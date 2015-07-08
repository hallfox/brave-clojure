(ns clojure-noob.core

  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "I'm a little teapot!"))

(defn train
  []
  (println "Choo choo!"))

(if true
  (do (println "Success!")
      "abra cadabra")
  (do (println "Failure :(")
      "hocus pocus"))

(when true
  (println "Success again!")
  "abra cadabra")

(def failed-protagonist-names
  ["Larry Potter"
   "Doreen the Explorer"
   "The Incredible Bulk"])

(def severity :mild)
(def error-message "OH GOD! IT'S A DISASTER! WE'RE ")
(if (= severity :mild)
  (def error-message (str error-message "MILDLY INCONVENIENCED!"))
  (def error-message (str error-message "DOOOOOOOOOOOMED!")))

(nil? 1)
(nil? nil)

(= 1 1)
(= nil nil)
(= 1 2)

(def name "ChewbaccaX")
(str "\"Ugglglglgglglglglglglgllgll\" - " name)

;; Maps
(get {:a 0 :b 1} :b) ;; get function
(get {:a 0 :b {:c "ho hum"}} :b)
(get {:a 0 :b 1} :c "UNICORNS")
(get-in {:a 0 :b {:c "ho hum"}} [:b :c]) ;; get-in for nested maps
({:name "The human Coffee Pot"} :name) ;; Treat map like func
(:name {:name "The human Coffee Pot"}) ;; Or lookup by keyword

;; Vectors
(def vector-intro [1 2 3 4])
(conj vector-intro 5) ;; Add to end of vector
(vector "creepy" "full" "moon") ;; Can also use vector function
