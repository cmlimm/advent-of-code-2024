(ns hystorian-histeria
  (:require [clojure.string :as string]))

(defn string-to-numbers
  [string-list]
  (map #(Integer/parseInt %) (string/split string-list #"   ")))

(defn split-sort-sub
  [list-of-pairs]
  (let [firsts (sort (map first list-of-pairs))
        seconds (sort (map last list-of-pairs))]
    (reduce + (map #(abs (- %1 %2)) firsts seconds))))

(def part1
  (->> (slurp "input.in")
       (clojure.string/split-lines)
       (map string-to-numbers)
       (split-sort-sub)))

part1

(defn split-count-mult
  [list-of-pairs]
  (let [firsts (map first list-of-pairs)
        seconds (frequencies (map last list-of-pairs))]
  (reduce + (map #(* % (get seconds % 0)) firsts))))

(def part2
  (->> (slurp "input.in")
       (clojure.string/split-lines)
       (map string-to-numbers)
       (split-count-mult)))

part2
