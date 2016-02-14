

    ; Pei Wang's "Non-Axiomatic Logic" specified with a math. notation inspired DSL with given intiutive explainations:

    ; The rules of NAL can be interpreted by considering the intiution behind the following two relations:
    ; Statement:                 (A --> B):               A can stand for B
    ; Statement about Statement: (A ==> B): If A is true so is/will be B
    ; --> is a relation in meaning of terms while ==> is a relation of truth between statements.

    ; Revision 
    ; When a given belief is challenged by new experience a new belief2 with same content (and disjoint evidental base) 
    ; a new revised task which sums up the evidence of both belief and belief2 is derived:

    ; A A |- A  [:t/revision] (Commented out because it is already handled by belief management in java)

    ; Similarity to Inheritance

    (S --> P) (S <-> P) |- (S --> P) :pre [:question?] :post [:t/structural-intersection :p/judgment]

    ; Inheritance to Similarity

    (S <-> P) (S --> P) |- (S <-> P) :pre [:question?] :post [:t/structural-abduction :p/judgment]

    ; Set Definition Similarity to Inheritance

    (S <-> #{P})   S |- (S --> #{P}) :post [:t/identity :d/identity :allow-backward]
    (S <-> #{P})  #{P} |- (S --> #{P}) :post [:t/identity :d/identity :allow-backward]
    ([S] <-> P) [S] |- ([S] --> P) :post [:t/identity :d/identity :allow-backward]
    ([S] <-> P)   P |- ([S] --> P) :post [:t/identity :d/identity :allow-backward]

    (#{S} <-> #{P})  #{S} |- (#{P} --> #{S}) :post [:t/identity :d/identity :allow-backward]
    (#{S} <-> #{P})  #{P} |- (#{P} --> #{S}) :post [:t/identity :d/identity :allow-backward]
    ([S] <-> [P]) [S] |- ([P] --> [S]) :post [:t/identity :d/identity :allow-backward]
    ([S] <-> [P]) [P] |- ([P] --> [S]) :post [:t/identity :d/identity :allow-backward]

    ; Set Definition Unwrap

    (#{S} <-> #{P})  #{S} |- (S <-> P) :post [:t/identity :d/identity :allow-backward]
    (#{S} <-> #{P})  #{P} |- (S <-> P) :post [:t/identity :d/identity :allow-backward]
    ([S] <-> [P]) [S] |- (S <-> P) :post [:t/identity :d/identity :allow-backward]
    ([S] <-> [P]) [P] |- (S <-> P) :post [:t/identity :d/identity :allow-backward]

    ; Nothing is more specific than a instance so it's similar

    (S --> #{P})   S  |- (S <-> #{P}) :post [:t/identity :d/identity :allow-backward]
    (S --> #{P}) #{P} |- (S <-> #{P}) :post [:t/identity :d/identity :allow-backward]

    ; nothing is more general than a property so it's similar

    ([S] --> P) [S] |- ([S] <-> P) :post [:t/identity :d/identity :allow-backward]
    ([S] --> P)   P |- ([S] <-> P) :post [:t/identity :d/identity :allow-backward]

    ; Truth-value functions: see TruthFunctions.java

    ; Immediate Inference 
    ; If S can stand for P P can to a certain low degree also represent the class S
    ; If after S usually P happens then it might be a good guess that usually before P happens S happens.

    (P --> S) (S --> P) |- (P --> S) :pre [:question?] :post [:t/conversion :p/judgment]
    (P --> S) (S --> P) |- (P --> S) :pre [:question?] :post [:t/conversion :p/judgment]
    (P ==> S) (S ==> P) |- (P ==> S) :pre [:question?] :post [:t/conversion :p/judgment]
    (P =|> S) (S =|> P) |- (P =|> S) :pre [:question?] :post [:t/conversion :p/judgment]
    (P =\> S) (S =/> P) |- (P =\> S) :pre [:question?] :post [:t/conversion :p/judgment]
    (P =/> S) (S =\> P) |- (P =/> S) :pre [:question?] :post [:t/conversion :p/judgment]

    ; "If not smoking lets you be healthy being not healthy may be the result of smoking"

    ( --S ==> P)    P |- ( --P ==> S) :post [:t/contraposition :allow-backward]
    ( --S ==> P)  --S |- ( --P ==> S) :post [:t/contraposition :allow-backward]
    ( --S =|> P)    P |- ( --P =|> S) :post [:t/contraposition :allow-backward]
    ( --S =|> P)  --S |- ( --P =|> S) :post [:t/contraposition :allow-backward]
    ( --S =/> P)    P |- ( --P =\> S) :post [:t/contraposition :allow-backward]
    ( --S =/> P)  --S |- ( --P =\> S) :post [:t/contraposition :allow-backward]
    ( --S =\> P)    P |- ( --P =/> S) :post [:t/contraposition :allow-backward]
    ( --S =\> P)  --S |- ( --P =/> S) :post [:t/contraposition :allow-backward]

    ; A belief b <f c> is equal to --b <1-f c>  which is the negation rule:

    (A --> B) A |- --(A --> B) :post [:t/negation :d/negation :allow-backward]
    (A --> B) B |- --(A --> B) :post [:t/negation :d/negation :allow-backward]
  --(A --> B) A |-   (A --> B) :post [:t/negation :d/negation :allow-backward]
  --(A --> B) B |-   (A --> B) :post [:t/negation :d/negation :allow-backward]

    (A <-> B) A |- --(A <-> B) :post [:t/negation :d/negation :allow-backward]
    (A <-> B) B |- --(A <-> B) :post [:t/negation :d/negation :allow-backward]
  --(A <-> B) A |-   (A <-> B) :post [:t/negation :d/negation :allow-backward]
  --(A <-> B) B |-   (A <-> B) :post [:t/negation :d/negation :allow-backward]

    (A ==> B) A |- --(A ==> B) :post [:t/negation :d/negation :allow-backward :order-for-all-same]
    (A ==> B) B |- --(A ==> B) :post [:t/negation :d/negation :allow-backward :order-for-all-same]
  --(A ==> B) A |-   (A ==> B) :post [:t/negation :d/negation :allow-backward :order-for-all-same]
  --(A ==> B) B |-   (A ==> B) :post [:t/negation :d/negation :allow-backward :order-for-all-same]

    (A <=> B) A |- --(A <=> B) :post [:t/negation :d/negation :allow-backward :order-for-all-same]
    (A <=> B) B |- --(A <=> B) :post [:t/negation :d/negation :allow-backward :order-for-all-same]
  --(A <=> B) A |-   (A <=> B) :post [:t/negation :d/negation :allow-backward :order-for-all-same]
  --(A <=> B) B |-   (A <=> B) :post [:t/negation :d/negation :allow-backward :order-for-all-same]

    ; TODO: probably make simpler by just allowing it for all tasks in general

    ; inheritance-based syllogism 
    ;       (A --> B) ------- (B --> C)
    ;            \               /
    ;             \             /
    ;              \           /
    ;               \         /
    ;                (A --> C)
    ; If A is a special case of B and B is a special case of C so is A a special case of C (strong) the other variations are hypotheses (weak)

    (A --> B) (B --> C) |- (A --> C) :pre [#(not= A C)] :post [:t/deduction :d/strong :allow-backward]
    (A --> B) (A --> C) |- (C --> B) :pre [#(not= B C)] :post [:t/abduction :d/weak :allow-backward]
    (A --> C) (B --> C) |- (B --> A) :pre [#(not= A B)] :post [:t/induction :d/weak :allow-backward]
    (A --> B) (B --> C) |- (C --> A) :pre [#(not= C A)] :post [:t/exemplification :d/weak :allow-backward]

    ; similarity from inheritance 
    ; If S is a special case of P and P is a special case of S then S and P are similar

    (S --> P) (P --> S) |- (S <-> P) :post [:t/intersection :d/strong :allow-backward]

    ; inheritance from similarty <- TODO check why this one was missing 

    (S <-> P) (P --> S) |- (S --> P) :post [:t/reduce-conjunction :d/strong :allow-backward]

    ; similarity-based syllogism 
    ; If P and S are a special case of M then they might be similar (weak) 
    ; also if P and S are a general case of M

    (P --> M) (S --> M) |- (S <-> P) :pre [#(not= S P)] :post [:t/comparison :d/weak :allow-backward]
    (M --> P) (M --> S) |- (S <-> P) :pre [#(not= S P)] :post [:t/comparison :d/weak :allow-backward]

    ; If M is a special case of P and S and M are similar then S is also a special case of P (strong)

    (M --> P) (S <-> M) |- (S --> P) :pre [#(not= S P)] :post [:t/analogy :d/strong :allow-backward]
    (P --> M) (S <-> M) |- (P --> S) :pre [#(not= S P)] :post [:t/analogy :d/strong :allow-backward]
    (M <-> P) (S <-> M) |- (S <-> P) :pre [#(not= S P)] :post [:t/resemblance :d/strong :allow-backward]

    ; inheritance-based composition 
    ; If P and S are in the intension/extension of M then union/difference and intersection can be built:

    (P --> M) (S --> M) |- ((S | P) --> M) :pre [(not_set S) (not_set P) #(not= S P) (no_common_subterm S P)] :post [:t/intersection]
                                                                                            ((S & P) --> M) :post [:t/union] 
                                                                                            ((P ~ S) --> M) :post [:t/difference]

    (M --> P) (M --> S) |- (M --> (P & S)) :pre [(not_set S) (not_set P) #(not= S P) (no_common_subterm S P)] :post [:t/intersection]
                                                                                            (M --> (P | S)) :post [:t/union] 
                                                                                            (M --> (P - S)) :post [:t/difference]

    ; inheritance-based decomposition 
    ; if (S --> M) is the case and ((| S A_1..n) --> M) is not the case then ((| A_1..n) --> M) is not the case hence :t/decompose-positive-negative-negative

    (S --> M) ((| S A_1..n) --> M) |- ((| A_1..n) --> M) :post [:t/decompose-positive-negative-negative]
    (S --> M) ((& S A_1..n) --> M) |- ((& A_1..n) --> M) :post [:t/decompose-negative-positive-positive]
    (S --> M) ((S ~ P) --> M) |- (P --> M) :post [:t/decompose-positive-negative-positive]
    (S --> M) ((P ~ S) --> M) |- (P --> M) :post [:t/decompose-negative-negative-negative]

    (M --> S) (M --> (& S A_1..n)) |- (M --> (& A_1..n)) :post [:t/decompose-positive-negative-negative]
    (M --> S) (M --> (| S A_1..n)) |- (M --> (| A_1..n)) :post [:t/decompose-negative-positive-positive]
    (M --> S) (M --> (S - P)) |- (M --> P) :post [:t/decompose-positive-negative-positive]
    (M --> S) (M --> (P - S)) |- (M --> P) :post [:t/decompose-negative-negative-negative]

    ; Set comprehension:

    (C --> A) (C --> B) |- (C --> R) :pre [(set_ext A) (union A B R)] :post [:t/union]
    (C --> A) (C --> B) |- (C --> R) :pre [(set_int A) (union A B R)] :post [:t/intersection]
    (A --> C) (B --> C) |- (R --> C) :pre [(set_ext A) (union A B R)] :post [:t/intersection]
    (A --> C) (B --> C) |- (R --> C) :pre [(set_int A) (union A B R)] :post [:t/union]

    (C --> A) (C --> B) |- (C --> R) :pre [(set_ext A) (intersection A B R)] :post [:t/intersection]
    (C --> A) (C --> B) |- (C --> R) :pre [(set_int A) (intersection A B R)] :post [:t/union]
    (A --> C) (B --> C) |- (R --> C) :pre [(set_ext A) (intersection A B R)] :post [:t/union]
    (A --> C) (B --> C) |- (R --> C) :pre [(set_int A) (intersection A B R)] :post [:t/intersection]

    (C --> A) (C --> B) |- (C --> R) :pre [(difference A B R)] :post [:t/difference]
    (A --> C) (B --> C) |- (R --> C) :pre [(difference A B R)] :post [:t/difference]

    ; Set element takeout:

    (C --> #{A_1..n}) C |- (C --> #{A_i}) :post [:t/structural-deduction]
    (C --> [A_1..n]) C |- (C --> [A_i]) :post [:t/structural-deduction]
    (#{A_1..n} --> C) C |- (#{A_i} --> C) :post [:t/structural-deduction]
    ([A_1..n] --> C) C |- ([A_i] --> C) :post [:t/structural-deduction]

    ; NAL3 single premise inference:

    ((| A_1..n) --> M) M |- (A_i --> M) :post [:t/structural-deduction]
    (M --> (& A_1..n)) M |- (M --> A_i) :post [:t/structural-deduction]

    ((B ~ G) --> S) S |- (B --> S) :post [:t/structural-deduction]
    (R --> (B - S)) R |- (R --> B) :post [:t/structural-deduction]

    ; NAL4 - Transformations between products and images: 
    ; Relations and transforming them into different representations so that arguments and the relation it'self can become the subject or predicate

    ((A_1..n) --> M) A_i |- (A_i --> (/ M A_1..A_i.(substitute _)..A_n )) :post [:t/identity :d/identity]

    (M --> (A_1..n)) A_i |- ((\ M A_1..A_i.(substitute _)..A_n ) --> A_i) :post [:t/identity :d/identity]

    (A_i --> (/ M A_1..A_i.(substitute _)..A_n )) M |- ((A_1..n) --> M) :post [:t/identity :d/identity]

    ((\ M A_1..A_i.(substitute _)..A_n ) --> A_i) M |- (M --> (A_1..n)) :post [:t/identity :d/identity]

    ; implication-based syllogism 
    ; (A ==> B) ------- (B ==> C)
    ; \               /
    ; \             /
    ; \           /
    ; \         /
    ; (A ==> C)
    ; If after S M happens and after M P happens so P happens after S

    (M ==> P) (S ==> M) |- (S ==> P) :pre [#(not= S P)] :post [:t/deduction :order-for-all-same :allow-backward]

    (P ==> M) (S ==> M) |- (S ==> P) :pre [#(not= S P)] :post [:t/induction :allow-backward]
    (P =|> M) (S =|> M) |- (S =|> P) :pre [#(not= S P)] :post [:t/induction :allow-backward]
    (P =/> M) (S =/> M) |- (S =|> P) :pre [#(not= S P)] :post [:t/induction :allow-backward]
    (P =\> M) (S =\> M) |- (S =|> P) :pre [#(not= S P)] :post [:t/induction :allow-backward]

    (M ==> P) (M ==> S) |- (S ==> P) :pre [#(not= S P)] :post [:t/abduction :allow-backward]
    (M =/> P) (M =/> S) |- (S =|> P) :pre [#(not= S P)] :post [:t/abduction :allow-backward]
    (M =|> P) (M =|> S) |- (S =|> P) :pre [#(not= S P)] :post [:t/abduction :allow-backward]
    (M =\> P) (M =\> S) |- (S =|> P) :pre [#(not= S P)] :post [:t/abduction :allow-backward]

    (P ==> M) (M ==> S) |- (S ==> P) :pre [#(not= S P)] :post [:t/exemplification :allow-backward]
    (P =/> M) (M =/> S) |- (S =\> P) :pre [#(not= S P)] :post [:t/exemplification :allow-backward]
    (P =\> M) (M =\> S) |- (S =/> P) :pre [#(not= S P)] :post [:t/exemplification :allow-backward]
    (P =|> M) (M =|> S) |- (S =|> P) :pre [#(not= S P)] :post [:t/exemplification :allow-backward]

    ; implication to equivalence 
    ; If when S happens P happens and before P happens S has happened then they are truth-related equivalent

    (S ==> P) (P ==> S) |- (S <=> P) :pre [#(not= S P)] :post [:t/intersection :allow-backward]
    (S =|> P) (P =|> S) |- (S <|> P) :pre [#(not= S P)] :post [:t/intersection :allow-backward]
    (S =/> P) (P =\> S) |- (S </> P) :pre [#(not= S P)] :post [:t/intersection :allow-backward]
    (S =\> P) (P =/> S) |- (P </> S) :pre [#(not= S P)] :post [:t/intersection :allow-backward]

    ; equivalence-based syllogism 
    ; Same as for inheritance again

    (P ==> M) (S ==> M) |- (S <=> P) :pre [#(not= S P)] :post [:t/comparison :allow-backward]
    (P =/> M) (S =/> M) |- (S <|> P) :pre [#(not= S P)] :post [:t/comparison :allow-backward]
                                            (S </> P) :post [:t/comparison :allow-backward] 
                                            (P </> S) :post [:t/comparison :allow-backward]
    (P =|> M) (S =|> M) |- (S <|> P) :pre [#(not= S P)] :post [:t/comparison :allow-backward]
    (P =\> M) (S =\> M) |- (S <|> P) :pre [#(not= S P)] :post [:t/comparison :allow-backward]
                                            (S </> P) :post [:t/comparison :allow-backward] 
                                            (P </> S) :post [:t/comparison :allow-backward]

    (M ==> P) (M ==> S) |- (S <=> P) :pre [#(not= S P)] :post [:t/comparison :allow-backward]
    (M =/> P) (M =/> S) |- (S <|> P) :pre [#(not= S P)] :post [:t/comparison :allow-backward]
                                            (S </> P) :post [:t/comparison :allow-backward] 
                                            (P </> S) :post [:t/comparison :allow-backward]
    (M =|> P) (M =|> S) |- (S <|> P) :pre [#(not= S P)] :post [:t/comparison :allow-backward]

    ; Same as for inheritance again

    (M ==> P) (S <=> M) |- (S ==> P) :pre [#(not= S P)] :post [:t/analogy :allow-backward]
    (M =/> P) (S </> M) |- (S =/> P) :pre [#(not= S P)] :post [:t/analogy :allow-backward]
    (M =/> P) (S <|> M) |- (S =/> P) :pre [#(not= S P)] :post [:t/analogy :allow-backward]
    (M =|> P) (S <|> M) |- (S =|> P) :pre [#(not= S P)] :post [:t/analogy :allow-backward]
    (M =\> P) (M </> S) |- (S =\> P) :pre [#(not= S P)] :post [:t/analogy :allow-backward]
    (M =\> P) (S <|> M) |- (S =\> P) :pre [#(not= S P)] :post [:t/analogy :allow-backward]

    (P ==> M) (S <=> M) |- (P ==> S) :pre [#(not= S P)] :post [:t/analogy :allow-backward]
    (P =/> M) (S <|> M) |- (P =/> S) :pre [#(not= S P)] :post [:t/analogy :allow-backward]
    (P =|> M) (S <|> M) |- (P =|> S) :pre [#(not= S P)] :post [:t/analogy :allow-backward]
    (P =\> M) (S </> M) |- (P =\> S) :pre [#(not= S P)] :post [:t/analogy :allow-backward]
    (P =\> M) (S <|> M) |- (P =\> S) :pre [#(not= S P)] :post [:t/analogy :allow-backward]

    (M <=> P) (S <=> M) |- (S <=> P) :pre [#(not= S P)] :post [:t/resemblance :order-for-all-same :allow-backward]
    (M </> P) (S <|> M) |- (S </> P) :pre [#(not= S P)] :post [:t/resemblance :allow-backward]
    (M <|> P) (S </> M) |- (S </> P) :pre [#(not= S P)] :post [:t/resemblance :allow-backward]

    ; implication-based composition 
    ; Same as for inheritance again

    (P ==> M) (S ==> M) |- ((P || S) ==> M) :pre [#(not= S P)] :post [:t/intersection]
                                            ((P && S) ==> M) :post [:t/union]
    (P =|> M) (S =|> M) |- ((P || S) =|> M) :pre [#(not= S P)] :post [:t/intersection]
                                            ((P &| S) =|> M) :post [:t/union]
    (P =/> M) (S =/> M) |- ((P || S) =/> M) :pre [#(not= S P)] :post [:t/intersection]
                                            ((P &| S) =/> M) :post [:t/union]
    (P =\> M) (S =\> M) |- ((P || S) =\> M) :pre [#(not= S P)] :post [:t/intersection]
                                            ((P &| S) =\> M) :post [:t/union]

    (M ==> P) (M ==> S) |- (M ==> (P && S)) :pre [#(not= S P)] :post [:t/intersection]
                                            (M ==> (P || S)) :post [:t/union]
    (M =/> P) (M =/> S) |- (M =/> (P &| S)) :pre [#(not= S P)] :post [:t/intersection]
                                            (M =/> (P || S)) :post [:t/union]
    (M =|> P) (M =|> S) |- (M =|> (P &| S)) :pre [#(not= S P)] :post [:t/intersection]
                                            (M =|> (P || S)) :post [:t/union]
    (M =\> P) (M =\> S) |- (M =\> (P &| S)) :pre [#(not= S P)] :post [:t/intersection]
                                            (M =\> (P || S)) :post [:t/union]

    (D =/> R) (D =\> K) |- (K =/> R) :pre [#(not= R K)] :post [:t/abduction]
                                            (R =\> K) :post [:t/induction] 
                                            (K </> R) :post [:t/comparison]
    ; implication-based decomposition 
    ; Same as for inheritance again

    (S ==> M) ((|| S A_1..n) ==> M) |- ((|| A_1..n) ==> M) :post [:t/decompose-positive-negative-negative :order-for-all-same]
    (S ==> M) ((&& S A_1..n) ==> M) |- ((&& A_1..n) ==> M) :post [:t/decompose-negative-positive-positive :order-for-all-same SequenceIntervals:FromPremises]

    (M ==> S) (M ==> (&& S A_1..n)) |- (M ==> (&& A_1..n)) :post [:t/decompose-positive-negative-negative :order-for-all-same SequenceIntervals:FromPremises]
    (M ==> S) (M ==> (|| S A_1..n)) |- (M ==> (|| A_1..n)) :post [:t/decompose-negative-positive-positive :order-for-all-same]

    ; conditional syllogism 
    ; If after M P usually happens and M happens it means P is expected to happen

    M (M ==> P) |- P :post [:t/deduction :d/induction :order-for-all-same] :pre [(shift_occurrence_forward unused "==>")]
    M (P ==> M) |- P :post [:t/abduction :d/deduction :order-for-all-same] :pre [(shift_occurrence_backward unused "==>")]
    M (S <=> M) |- S :post [:t/analogy :d/strong :order-for-all-same] :pre [(shift_occurrence_backward unused "<=>")]
    M (M <=> S) |- S :post [:t/analogy :d/strong :order-for-all-same] :pre [(shift_occurrence_forward unused "==>")]

    ; conditional composition: 
    ; They are let out for AGI purpose don't let the system generate conjunctions or useless <=> and ==> statements
    ; For this there needs to be a semantic dependence between both either by the predicate or by the subject 
    ; or a temporal dependence which acts as special case of semantic dependence
    ; These cases are handled by "Variable Introduction" and "Temporal Induction"

    ; P S |- (S ==> P) :pre [(no_common_subterm S P)] :post [:t/induction]
    ; P S |- (S <=> P) :pre [(no_common_subterm S P)] :post [:t/comparison]
    ; P S |- (P && S) :pre [(no_common_subterm S P)] :post [:t/intersection]
    ; P S |- (P || S) :pre [(no_common_subterm S P)] :post [:t/union]

    ; conjunction decompose

    (&& A_1..n) A_1 |- A_1 :post [:t/structural-deduction :d/structural-strong]
    (&/ A_1..n) A_1 |- A_1 :post [:t/structural-deduction :d/structural-strong]
    (&| A_1..n) A_1 |- A_1 :post [:t/structural-deduction :d/structural-strong]
    (&/ B A_1..n) B |- (&/ A_1..n) :pre [(task "!")] :post [:t/deduction :d/strong SequenceIntervals:FromPremises]

    ; propositional decomposition 
    ; If S is the case and (&& S A_1..n) is not the case it can't be that (&& A_1..n) is the case

    S (&/ S A_1..n) |- (&/ A_1..n) :post [:t/decompose-positive-negative-negative SequenceIntervals:FromPremises]
    S (&| S A_1..n) |- (&| A_1..n) :post [:t/decompose-positive-negative-negative]
    S (&& S A_1..n) |- (&& A_1..n) :post [:t/decompose-positive-negative-negative]
    S (|| S A_1..n) |- (|| A_1..n) :post [:t/decompose-negative-positive-positive]

    ; Additional for negation: https://groups.google.com/forum/#!topic/open-nars/g-7r0jjq2Vc

    S (&/ (-- S) A_1..n) |- (&/ A_1..n) :post [:t/decompose-negative-negative-negative SequenceIntervals:FromPremises]
    S (&| (-- S) A_1..n) |- (&| A_1..n) :post [:t/decompose-negative-negative-negative]
    S (&& (-- S) A_1..n) |- (&& A_1..n) :post [:t/decompose-negative-negative-negative]
    S (|| (-- S) A_1..n) |- (|| A_1..n) :post [:t/decompose-positive-positive-positive]

    ; multi-conditional syllogism 
    ; Inference about the pre/postconditions

    Y ((&& X A_1..n) ==> B) |- ((&& A_1..n) ==> B) :pre [(substitute_if_unifies "$" X Y)] :post [:t/deduction :order-for-all-same SequenceIntervals:FromPremises]
    ((&& M A_1..n) ==> C) ((&& A_1..n) ==> C) |- M :post [:t/abduction :order-for-all-same]

    ; Can be derived by NAL7 rules so this won't be necessary there (:order-for-all-same left out here)

    ; the first rule does not have :order-for-all-same because it would be invalid see: https://groups.google.com/forum/#!topic/open-nars/r5UJo64Qhrk
    ((&& A_1..n) ==> C) M |- ((&& M A_1..n) ==> C) :pre [(not_implication_or_equivalence M)] :post [:t/induction]
    ((&& A_1..n) =|> C) M |- ((&& M A_1..n) =|> C) :pre [(not_implication_or_equivalence M)] :post [:t/induction]
    ((&& A_1..n) =/> C) M |- ((&& M A_1..n) =/> C) :pre [(not_implication_or_equivalence M)] :post [:t/induction]
    ((&& A_1..n) =\> C) M |- ((&& M A_1..n) =\> C) :pre [(not_implication_or_equivalence M)] :post [:t/induction]
    (A ==> M) ((&& M A_1..n) ==> C) |- ((&& A A_1..n) ==> C) :post [:t/deduction :order-for-all-same SequenceIntervals:FromPremises]
    ((&& M A_1..n) ==> C) ((&& A A_1..n) ==> C) |- (A ==> M) :post [:t/induction :order-for-all-same]
    (A ==> M) ((&& A A_1..n) ==> C) |- ((&& M A_1..n) ==> C) :post [:t/abduction :order-for-all-same SequenceIntervals:FromPremises]

    ; variable introduction 
    ; Introduce variables by common subject or predicate

    (S --> M) (P --> M) |- ((P --> $X) ==> (S --> $X)) :pre [#(not= S P)] :post [:t/abduction]
                                            ((S --> $X) ==> (P --> $X)) :post [:t/induction] 
                                            ((P --> $X) <=> (S --> $X)) :post [:t/comparison] 
                                            (&& (S --> #Y) (P --> #Y)) :post [:t/intersection]

    (S --> M) (P --> M) |- ((&/ (P --> $X) I) =/> (S --> $X)) :pre [#(not= S P) (measure_time I)] :post [:t/induction Linkage:Temporal]
                                                             ((S --> $X) =\> (&/ (P --> $X) I)) :post [:t/abduction Linkage:Temporal] 
                                                             ((&/ (P --> $X) I) </> (S --> $X)) :post [:t/comparison Linkage:Temporal] 
                                                             (&/ (P --> #Y) I (S --> #Y)) :post [:t/intersection Linkage:Temporal]

    (S --> M) (P --> M) |- ((P --> $X) =|> (S --> $X)) :pre [#(not= S P) (concurrent Task Belief)] :post [:t/abduction Linkage:Temporal]
                                                                     ((S --> $X) =|> (P --> $X)) :post [:t/induction Linkage:Temporal] 
                                                                     ((P --> $X) <|> (S --> $X)) :post [:t/comparison Linkage:Temporal] 
                                                                     (&| (P --> #Y) (S --> #Y)) :post [:t/intersection Linkage:Temporal]

    (M --> S) (M --> P) |- (($X --> S) ==> ($X --> P)) :pre [#(not= S P)] :post [:t/induction]
                                            (($X --> P) ==> ($X --> S)) :post [:t/abduction] 
                                            (($X --> S) <=> ($X --> P)) :post [:t/comparison] 
                                            (&& (#Y --> S) (#Y --> P)) :post [:t/intersection]

    (M --> S) (M --> P) |- ((&/ ($X --> P) I) =/> ($X --> S)) :pre [#(not= S P) (measure_time I)] :post [:t/induction Linkage:Temporal]
                                                             (($X --> S) =\> (&/ ($X --> P) I)) :post [:t/abduction Linkage:Temporal] 
                                                             ((&/ ($X --> P) I) </> ($X --> S)) :post [:t/comparison Linkage:Temporal] 
                                                             (&/ (#Y --> P) I (#Y --> S)) :post [:t/intersection Linkage:Temporal]

    (M --> S) (M --> P) |- (($X --> S) =|> ($X --> P)) :pre [#(not= S P) (concurrent (M --> P) (M --> S))] :post [:t/induction Linkage:Temporal]
                                                                             (($X --> P) =|> ($X --> S)) :post [:t/abduction Linkage:Temporal] 
                                                                             (($X --> S) <|> ($X --> P)) :post [:t/comparison Linkage:Temporal] 
                                                                             (&| (#Y --> S) (#Y --> P)) :post [:t/intersection Linkage:Temporal]

    ; 2nd variable introduction 

    (A ==> (M --> P)) (M --> S) |- ((&& A ($X --> S)) ==> ($X --> P)) :pre [#(not= A (M --> S))] :post [:t/induction]
                                                             (&& (A ==> (#Y --> P)) (#Y --> S)) :post [:t/intersection]

    (&& (M --> P) A_1..n) (M --> S) |- (($Y --> S) ==> (&& ($Y --> P) A_1..n)) :pre [#(not= S P)] :post [:t/induction]
                                                         (&& (#Y --> S) (#Y --> P) A_1..n) :post [:t/intersection]

    (A ==> (P --> M)) (S --> M) |- ((&& A (P --> $X)) ==> (S --> $X)) :pre [#(not= S P) #(not= A (S --> M))] :post [:t/abduction]
                                                                             (&& (A ==> (P --> #Y)) (S --> #Y)) :post [:t/intersection]

    (&& (P --> M) A_1..n) (S --> M) |- ((S --> $Y) ==> (&& (P --> $Y) A_1..n)) :pre [#(not= S P)] :post [:t/abduction]
                                                         (&&  (S --> #Y) (P --> #Y) A_1..n) :post [:t/intersection]

    (A --> L) ((A --> S) ==> R) |- ((&& (#X --> L) (#X --> S)) ==> R) :post [:t/induction]
    (A --> L) ((&& (A --> S) A_1..n) ==> R) |- ((&& (#X --> L) (#X --> S) A_1..n) ==> R) :pre [(substitute A #X)] :post [:t/induction]

    ; dependent variable elimination 
    ; Decomposition with elimination of a variable

    B (&& A A_1..n) |- (&& A_1..n) :pre [(task ".") (substitute_if_unifies "#" A B)] :post [:t/anonymous-analogy :d/strong :order-for-all-same SequenceIntervals:FromPremises]

    ; conditional abduction by dependent variable

    ((A --> R) ==> Z) ((&& (#Y --> B) (#Y --> R) A_1..n) ==> Z) |- (A --> B) :post [:t/abduction]
    ((A --> R) ==> Z) ((&& (#Y --> B) (#Y --> R)) ==> Z) |- (A --> B) :post [:t/abduction]

    ; conditional deduction "An inverse inference has been implemented as a form of deduction" https://code.google.com/p/open-nars/issues/detail?id=40&can=1

    (U --> L) ((&& (#X --> L) (#X --> R)) ==> Z) |- ((U --> R) ==> Z) :post [:t/deduction]
    (U --> L) ((&& (#X --> L) (#X --> R) A_1..n) ==> Z) |- ((&& (U --> R) A_1..n) ==> Z) :pre [(substitute #X U)] :post [:t/deduction]

    ; independent variable elimination 

    B (A ==> C) |- C  [:t/deduction :order-for-all-same] :pre [(substitute_if_unifies "$" A B) (shift_occurrence_forward unused "==>")]
    B (C ==> A) |- C  [:t/abduction :order-for-all-same] :pre [(substitute_if_unifies "$" A B) (shift_occurrence_backward unused "==>")]

    B (A <=> C) |- C  [:t/deduction :order-for-all-same] :pre [(substitute_if_unifies "$" A B) (shift_occurrence_backward unused "<=>")]
    B (C <=> A) |- C  [:t/deduction :order-for-all-same] :pre [(substitute_if_unifies "$" A B) (shift_occurrence_forward unused "<=>")]

    ; second level variable handling rules 
    ; second level variable elimination (termlink level2 growth needed in order for these rules to work)

    (A --> K) (&& (#X --> L) (($Y --> K) ==> (&& A_1..n))) |- (&& (#X --> L) A_1..n) :pre [(substitute $Y A)] :post [:t/deduction]
    (A --> K) (($X --> L) ==> (&& (#Y --> K) A_1..n)) |- (($X --> L) ==> (&& A_1..n)) :pre [(substitute #Y A)] :post [:t/anonymous-analogy]

    ; precondition combiner inference rule (variable_unification6):

    ((&& C A_1..n) ==> Z) ((&& C B_1..m) ==> Z) |- ((&& A_1..n) ==> (&& B_1..m)) :post [:t/induction]
    ((&& C A_1..n) ==> Z) ((&& C B_1..m) ==> Z) |- ((&& B_1..m) ==> (&& A_1..n)) :post [:t/induction]
    (Z ==> (&& C A_1..n)) (Z ==> (&& C B_1..m)) |- ((&& A_1..n) ==> (&& B_1..m)) :post [:t/abduction]
    (Z ==> (&& C A_1..n)) (Z ==> (&& C B_1..m)) |- ((&& B_1..m) ==> (&& A_1..n)) :post [:t/abduction]

    ; NAL7 specific inference 
    ; Reasoning about temporal statements. those are using the ==> relation because relation in time is a relation of the truth between statements.

    X (XI ==> B) |- B  [:t/deduction :d/induction :order-for-all-same] :pre [(substitute_if_unifies "$" XI (&/ X /0)) (shift_occurrence_forward XI "==>")]
    X (BI ==> Y) |- BI  [:t/abduction :d/deduction :order-for-all-same] :pre [(substitute_if_unifies "$" Y X) (shift_occurrence_backward BI "==>")]

    ; Temporal induction: 
    ; When P and then S happened according to an observation by induction (weak) it may be that alyways after P usually S happens.

    P S |- ((&/ S I) =/> P) :pre [(measure_time I) (not_implication_or_equivalence P) (not_implication_or_equivalence S)] :post [:t/induction Linkage:Temporal]
                                                                                                   (P =\> (&/ S I)) :post [:t/abduction Linkage:Temporal] 
                                                                                                   ((&/ S I) </> P) :post [:t/comparison Linkage:Temporal] 
                                                                                                   (&/ S I P) :post [:t/intersection Linkage:Temporal]


    P S |- (S =|> P) :pre [(concurrent Task Belief) (not_implication_or_equivalence P) (not_implication_or_equivalence S)] :post [:t/induction Linkage:Temporal]
                                                                                                           (P =|> S) :post [:t/induction Linkage:Temporal] 
                                                                                                           (S <|> P) :post [:t/comparison Linkage:Temporal] 
                                                                                                           (&| S P) :post [:t/intersection Linkage:Temporal]

    ; backward inference is mostly handled by the rule transformation:

    ; T B |- C  [post] =>
    ; C B |- T  [post] :pre [:question?]
    ; C T |- B  [post] :pre [:question?]

    ; here now are the backward inference rules which should really only work on backward inference:

    (A --> S) (B --> S) |- (A --> B) :pre [:question?] :post [:p/question]
                                       (B --> A) :post [:p/question]
                                       (A <-> B) :post [:p/question]

    ; and the backward inference driven forward inference:

    ; NAL2:

    ([A] <-> [B]) (A <-> B) |- ([A] <-> [B]) :pre [:question?] :post [:t/belief-identity :p/judgment]
    (#{A} <-> #{B}) (A <-> B) |- (#{A} <-> #{B}) :pre [:question?] :post [:t/belief-identity :p/judgment]

    ([A] --> [B]) (A <-> B) |- ([A] --> [B]) :pre [:question?] :post [:t/belief-identity :p/judgment]
    (#{A} --> #{B}) (A <-> B) |- (#{A} --> #{B}) :pre [:question?] :post [:t/belief-identity :p/judgment]

    ; NAL3:

    ; composition on both sides of a statement:

    ((& B A_1..n) --> (& A A_1..n)) (B --> A) |- ((& B A_1..n) --> (& A A_1..n)) :pre [:question?] :post [:t/belief-structural-deduction :p/judgment]
    ((| B A_1..n) --> (| A A_1..n)) (B --> A) |- ((| B A_1..n) --> (| A A_1..n)) :pre [:question?] :post [:t/belief-structural-deduction :p/judgment]

    ((- S A) --> (- S B)) (B --> A) |- ((- S A) --> (- S B)) :pre [:question?] :post [:t/belief-structural-deduction :p/judgment]
    ((~ S A) --> (~ S B)) (B --> A) |- ((~ S A) --> (~ S B)) :pre [:question?] :post [:t/belief-structural-deduction :p/judgment]

    ; composition on one side of a statement:

    (W --> (| B A_1..n)) (W --> B) |- (W --> (| B A_1..n)) :pre [:question?] :post [:t/belief-structural-deduction :p/judgment]
    ((& B A_1..n) --> W) (B --> W) |- ((& B A_1..n) --> W) :pre [:question?] :post [:t/belief-structural-deduction :p/judgment]

    (W --> (- S B)) (W --> B) |- (W --> (- S B)) :pre [:question?] :post [:t/beliefStructuralDifference :p/judgment]
    ((~ S B) --> W) (B --> W) |- ((~ S B) --> W) :pre [:question?] :post [:t/beliefStructuralDifference :p/judgment]

    ; NAL4:

    ; composition on both sides of a statement:

    ((B P) --> Z) (B --> A) |- ((B P) --> (A P)) :pre [:question?] :post [:t/belief-structural-deduction :p/judgment]
    ((P B) --> Z) (B --> A) |- ((P B) --> (P A)) :pre [:question?] :post [:t/belief-structural-deduction :p/judgment]
    (B P) <-> Z) (B <-> A) |- ((B P) <-> (A P)) :pre [:question?] :post [:t/belief-structural-deduction :p/judgment]
    ((P B) <-> Z) (B <-> A) |- ((P B) <-> (P A)) :pre [:question?] :post [:t/belief-structural-deduction :p/judgment]
    ((\ N A _) --> Z) (N --> R) |- ((\ N A _) --> (\ R A _)) :pre [:question?] :post [:t/belief-structural-deduction :p/judgment]
    ((/ N _ B) --> Z) (S --> B) |- ((/ N _ B) --> (/ N _ S)) :pre [:question?] :post [:t/belief-structural-deduction :p/judgment]

    ; NAL5:

  --A    A |- --A  :pre [:question?] :post [:t/belief-negation :p/judgment]
    A  --A |-   A  :pre [:question?] :post [:t/belief-negation :p/judgment]

    ; compound composition one premise

    (|| B A_1..n) B |- (|| B A_1..n) :pre [:question?] :post [:t/belief-structural-deduction :p/judgment]
