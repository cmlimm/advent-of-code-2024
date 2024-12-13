(ns red-nosed-reports
  (:require [clojure.string :as string]))

(defn string-to-numbers
  [string-list]
  (map #(Integer/parseInt %) (string/split string-list #" ")))

(defn desc-asc?
  [list]
  (or (apply <= list) (apply >= list)))

(defn between-1-3?
  [pair]
  (let [diff (abs (apply - pair))]
  (and (<= diff 3) (>= diff 1))))

(defn every-diff?
  [list]
  (every? identity (map between-1-3? (partition 2 1 list))))

(defn both-conditions?
  [list]
  (and (every-diff? list) (desc-asc? list)))

(def part1
  (->> (slurp "input.in")
       (clojure.string/split-lines)
       (map string-to-numbers)
       (map both-conditions?)
       (filter identity)
       (count)))

part1

(defn concat-without-first
  [[first second]]
  (concat first (rest second)))

(defn check-for-every-dropped-level
  [list]
  (let [n (count list)
        range-n (range n)]
    (->> (map #(split-at % list) range-n)
         (map #(both-conditions? (concat-without-first %)))
         (some identity))))

(def part2
  (->> (slurp "input.in")
       (clojure.string/split-lines)
       (map string-to-numbers)
       (map check-for-every-dropped-level)
       (filter identity)
       (count)))

part2

; solution for a different problem:
; if dropping a level does not cause us to compare now-adjacent numbers
; (1 2 7) -> (1 _ 7) -> difference between 1 and 7 does not matter because there is an empty space

; wont delete because i spent time on this

; (defn desc-pairwise?
;   [list]
;   (map #(apply >= %) (partition 2 1 list)))

; (defn asc-pairwise?
;   [list]
;   (map #(apply <= %) (partition 2 1 list)))

; (defn diff-pairwise?
;   [list]
;   (map between-1-3? (partition 2 1 list)))

; (defn combined-conditions?
;   [list]
;   (let [diff-pairwise (diff-pairwise? list)
;         desc-diff (map #(and %1 %2) (desc-pairwise? list) diff-pairwise)
;         asc-diff (map #(and %1 %2) (asc-pairwise? list) diff-pairwise)
;         desc-count (count (filter not desc-diff))
;         asc-count (count (filter not asc-diff))]
;     (or (<= desc-count 1) (<= asc-count 1))))

; (def part2
;   (->> (slurp "input.in")
;        (clojure.string/split-lines)
;        (map string-to-numbers)
;        (map combined-conditions?)
;        (filter identity)
;        (count)))

; part2
