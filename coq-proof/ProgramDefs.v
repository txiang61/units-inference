Require Import Bool.
Require Import Lists.List.
Import List.ListNotations.
Require Import Lists.ListSet.
Require Import Arith.
Require Import Arith.EqNat.
Require Import Strings.String. Open Scope string_scope.
Open Scope core_scope.

Require Import TacticalLemmas.
Require Import UnitsDefs.
Require Import SubtypingDefs.
Require Import UnitsArithmetics.
Require Import IDDefs.
Require Import ValueDefs.
Require Import GammaDefs.
Require Import HeapDefs.
Require Import GammaHeapCorrespondence.
Require Import FieldDefs.
Require Import ExpressionDefs.
Require Import StatementDefs.

(* ======================================================= *)
Inductive Program : Type :=
  | program : Field_Declarations -> Statements -> Program.
Tactic Notation "prog_cases" tactic(first) ident(c) :=
  first;
  [ Case_aux c "program"
  ].
Hint Constructors Program.

Notation "{ fds s }" := (program fds s) (at level 1).

(* ======================================================= *)
Reserved Notation "'prog:' g '|-' p 'in' post_gamma" (at level 40).
Inductive prog_has_type : Gamma -> Program -> Gamma -> Prop :=
  | T_Program : forall (g post_gamma : Gamma) (fds : Field_Declarations) (s : Statements),
    fds: g |- fds in post_gamma ->
    stmt: post_gamma |- s ->
    prog: g |- program fds s in post_gamma
where "'prog:' g '|-' p 'in' post_gamma" := (prog_has_type g p post_gamma).
Tactic Notation "prog_has_type_cases" tactic(first) ident(c) :=
  first;
  [ Case_aux c "T_Program"
  ].
Hint Constructors prog_has_type.

(* ======================================================= *)
Reserved Notation " p1 'prog==>' p2 " (at level 8).
Inductive prog_small_step : prod Heap Program -> prod Heap Program -> Prop :=
  | ST_PROG_FieldDefs_Step : forall h h' fds fds' s,
    ( h, fds ) fds==> ( h', fds' ) ->
    ( h, program fds s ) prog==> ( h', program fds' s )
  | ST_PROG_Statementss_Step : forall h h' s s',
    ( h, s ) stmt==> ( h', s' ) ->
    ( h, program FD_Empty s ) prog==> ( h', program FD_Empty s' )
where " p1 'prog==>' p2 " := (prog_small_step p1 p2).
Tactic Notation "prog_small_step_cases" tactic(first) ident(c) :=
  first;
  [ Case_aux c "ST_PROG_FieldDefs_Step"
  | Case_aux c "ST_PROG_Statementss_Step"
  ].
Hint Constructors prog_small_step.

(* ======================================================= *)
(* step is deterministic *)
Theorem prog_small_step_deterministic:
  forall p p1 p2,
    p prog==> p1 ->
    p prog==> p2 ->
    p1 = p2.
Proof.
  intros p p1 p2 Hp1.
  generalize dependent p2.
  prog_small_step_cases (induction Hp1) Case.
  Case "ST_PROG_FieldDefs_Step".
    intros p2 Hp2; inversion Hp2; subst.
      assert ((h', fds') = (h'0, fds'0)) as HfdsEQ. eapply fds_small_step_deterministic.
        apply H. apply H4. inversion HfdsEQ; subst. reflexivity.
      inversion H.
  Case "ST_PROG_Statementss_Step".
    intros p2 Hp2; inversion Hp2; subst.
      inversion H4.
      assert ((h', s') = (h'0, s'0)) as HsEQ. eapply stmt_small_step_deterministic.
        apply H. apply H3. inversion HsEQ; subst. reflexivity.
Qed.

(* ======================================================= *)
Inductive prog_normal_form : Program -> Prop :=
  | V_PROG_NF : prog_normal_form (program FD_Empty STMT_Empty).

(* ======================================================= *)
Theorem prog_progress : forall (g1 g2 : Gamma) (h : Heap) (p : Program),
  prog: g1 |- p in g2 ->
  gh: g2 |- h OK ->
  prog_normal_form p \/ exists (h' : Heap) (p' : Program), (h, p) prog==> (h', p').
Proof.
  (* by induction on typing of prog *)
  intros g1 g2 h p HT HGH.
  prog_has_type_cases (induction HT) Case; subst.
  Case "T_Program".
    assert (FD_Normal_Form fds \/ exists heap' fds', (h, fds) fds==> (heap', fds')).
      eapply fds_progress. apply H.
    assert (STMT_Normal_Form s \/ exists (h' : Heap) (s' : Statements), (h, s) stmt==> (h', s')).
      eapply stmt_progress. apply H0. apply HGH.
    destruct H1; subst.
    (* Case : FD is normal form *)
      destruct H2; subst.
      (* Case : STMT is normal form *)
        destruct H1; subst. destruct H2; subst. left. apply V_PROG_NF.
      (* Case : STMT can take a step *)
        destruct H1; subst. right. destruct H2 as [h']. destruct H1 as [s'].
        exists h'. exists (program FD_Empty s').
        apply ST_PROG_Statementss_Step. apply H1.
    (* Case : FD can take a step *)
      destruct H1 as [h']. destruct H1 as [fds']. right.
      exists h'. exists (program fds' s).
      apply ST_PROG_FieldDefs_Step. apply H1.
Qed.

(* ======================================================= *)

Definition Gamma_Extend_Prog (g : Gamma) (p : Program) : Gamma :=
  match p with
  | program fds s => Gamma_Extend_Fields g fds
  end.

(* ======================================================= *)
Theorem prog_preservation : forall (g post_gamma : Gamma) (h h' : Heap) (p p' : Program),
  prog: g |- p in post_gamma ->
  gh: post_gamma |- h OK ->
  (h, p) prog==> (h', p') ->
  gh: post_gamma |- h' OK /\ prog: (Gamma_Extend_Prog g p) |- p' in post_gamma.
Proof.
  (* by induction on typing of program *)
  intros g post_gamma h h' p p' HT HGH HS.
  (* we prove for programs typed starting from empty gamma, rather than all possible gamma *)
  remember empty_gamma as Gamma.
  generalize dependent p'. generalize dependent h'.
  prog_has_type_cases (induction HT) Case; intros h' p' HS; subst.
  Case "T_Program".
    destruct p' as [fds' s'].
    prog_small_step_cases (inversion HS) SCase; subst.
    SCase "ST_PROG_FieldDefs_Step".
      inversion H2; subst.
      unfold Gamma_Extend_Prog. simpl.
      eapply fds_preservation in H.
      destruct H.
        split.
        (* first prove that post_gamma |- h' OK *)
          apply H.
        (* then prove that prog: (Gamma_Extend_Prog empty_gamma p) |- p' in post_gamma. *)
          apply T_Program.
            apply H1.
            apply H0.
        apply HGH.
        apply H2.
    SCase "ST_PROG_Statementss_Step".
      simpl. (* in statement step, the input gamma to type p' does not change *)
        eapply stmt_preservation in H0.
        split.
        (* first prove that post_gamma |- h' OK *)
          apply H0.
        (* then prove that prog: g |- {FD_Empty s'} in post_gamma *)
          apply T_Program.
            apply H.
            apply H0.
        apply HGH.
        apply H2.
Qed.
