(ns mult-it-over
  (:require [clojure.string :as string]))

; find all correct commands using the `mul\(\d{1,3},\d{1,3}\)` regexp in the text editor
; remove `mul` and brackets
; new input:
; 13,14
; 1,5
; 3,456
; and so on

(defn string-to-numbers
  [string-list]
  (map #(Integer/parseInt %) (string/split string-list #",")))

(def part1
  (->> (slurp "input.in")
       (clojure.string/split-lines)
       (map string-to-numbers)
       (map #(apply * %))
       (reduce +)))

part1

; remove all line breaks because that seems to be important for some reason (the next step doesn't work otherwise)
; remove everything between `don't()` and `do()` using the `don't\(\).*?do\(\)` regexp
; repeat actions for part 1
