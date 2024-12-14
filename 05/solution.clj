(ns print-queue
  (:require [clojure.string :as string]))

(defn string-to-numbers
  [string-list]
  (map #(Integer/parseInt %) (string/split string-list #"\|")))

(defn add-to-map
  [map [first second]]
  (if (contains? map first)
    (assoc-in map [first (count (get map first))] second)
    (assoc-in map [first] [second])))

(def comparator-map
  (->> (slurp "input.in")
       (clojure.string/split-lines)
       (map string-to-numbers)
       (reduce add-to-map {})))

comparator-map

(defn in?
  [coll elm]  
  (some #(= elm %) coll))

; stole the clojure source code for >= and replaced the actual comparison logic
; returns `true` if `x` can be placed BEFORE `y`
(defn special-compare
  ([x] true)
  ([x y] (not (in? (get comparator-map y) x)))
  ([x y & more]
   (if (special-compare x y)
     (if (next more)
       (recur y (first more) (next more))
       (special-compare y (first more)))
     false)))

(defn string-to-numbers-comma
  [string-list]
  (map #(Integer/parseInt %) (string/split string-list #",")))

(def numbers-list
  (->> (slurp "other_input.in")
       (clojure.string/split-lines)
       (map string-to-numbers-comma)))

; stole from https://stackoverflow.com/questions/15614420/finding-the-middle-element-from-a-vector-in-clojure
(defn middle-value [v]
  (nth v (quot (count v) 2)))

; the same way (apply >= list) returns true if the list is ordered
; (apply special-compare list) returns true is the list is ordered by our special rules 
(def part1
  (->> (filter #(apply special-compare %) numbers-list)
       (map middle-value)
       (reduce +)))

part1

(defn actual-comparator
  [x y]
  (if (special-compare x y) -1 1))

(def part2
  (->> (filter #(not (apply special-compare %)) numbers-list)
       (map #(sort-by identity special-compare %))
       (map middle-value)
       (reduce +)))

part2

