(ns fwpd.core
  (:require [clojure.string :as s]))

(def filename "suspects.csv")

;; Need a way to translate from CSV fields to keywords
(def headers->keywords {"Name" :name
                        "Glitter Index" :glitter-index})

(def keywords->headers {:name "Name"
                        :glitter-index "Glitter Index"})

(defn str->int
  [str]
  (Integer. str))

;; Need to convert text data into numbers
(def conversions {:name identity
                 :glitter-index str->int})

(defn parse
  "Convert a csv into rows of columns"
  [string]
  (map #(s/split % #",")
       (s/split string #"\n")))

(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (let [;; headers becomes the seq (:name :glitter-index)
        headers (map #(get headers->keywords %) (first rows))
        ;; unmapped-rows becomes the seq
        ;; (["Edward Cullen" "10"] ["Bella Swan" "0"] ...)
        unmapped-rows (rest rows)]
    (map (fn [unmapped-row]
           ;; We're going to use map to associate each header with its
           ;; column. Since map returns a seq, we use "into" to convert
           ;; it into a map.
           (into {}
                 ;; notice we're passing multiple collections to map
                 (map (fn [header column]
                        ;; associate the header with the converted column
                        [header ((get conversions header) column)])
                      headers
                      unmapped-row)))
         unmapped-rows)))

(defn glitter-filter
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))

(defn suspect-names
  "Turns a profile of suspects into a list of their names"
  [suspects]
  (into '() (map :name suspects)))

(def prepend conj)

(defn validate
  "Check :name and :glitter-index are present during a prepend"
  [validations record]
  (every? #(some? (get record %)) validations))

(def comma-join (partial clojure.string/join ","))

(defn csvify
  "Turns suspects back into csv"
  [records]
  (let [fields (keys (first records))
        header (comma-join (map #(get keywords->headers %) fields))
        data (map (fn [record]
                    (comma-join (vals record)))
                  records)]
    (clojure.string/join "\n" (conj data header))))
