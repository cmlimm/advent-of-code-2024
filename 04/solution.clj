(ns ceres-search
  (:require [clojure.string :as string]))

(defn pad-back [n list]
  (concat list (take n (repeat \*))))

(defn pad-front [n list]
  (concat (take n (repeat \*)) list))

(defn transpose [m]
  (apply mapv vector m))

; pad every line so diagonals become vertical lines:
; LOLOL
;  LOLOL
;   LOLOL
; transpose the text
; paste the text into the text editor and find every XMAS and SAMX
(def pre-diag-to-left
  (->> (slurp "input.in")
       (clojure.string/split-lines)
       (map seq)
       (map #(pad-back (- 139 %1) %2) (range 140))
       (map #(pad-front %1 %2) (range 140))))
(def diag-to-left (map string/join (transpose pre-diag-to-left)))
diag-to-left

; pad the lines the other way:
;   LOLOL
;  LOLOL
; LOLOL
(def pre-diag-to-right
  (->> (slurp "input.in")
       (clojure.string/split-lines)
       (map seq)
       (map #(pad-front (- 139 %1) %2) (range 140))
       (map #(pad-back %1 %2) (range 140))))
(def diag-to-right (map string/join (transpose pre-diag-to-right)))
diag-to-right

; just transpose
(def pre-transposed
  (->> (slurp "input.in")
       (clojure.string/split-lines)
       (map seq)))
(def transposed (map string/join (transpose pre-transposed)))
transposed

; calculate XMAS and SAMX for normal text, transposed text and two (padded + tranposed) texts
(reduce + `(226 240 211 197 387 430 420 385))

; part 2
; won't do