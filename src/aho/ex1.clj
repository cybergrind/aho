(ns aho.ex1
  (:require [clojure.set :refer [difference]]))


(defn vertice
  ([name] {:name name, :edges #{} :color nil})
  ([name edges] {:name name, :edges edges, :color nil}))
   
(defn graph
  ([] {:vs #{}})
  ([vertices] {:vs vertices}))

(defn add-vertice [g v]
  (println "add vertice" g v)
  (let [vertice
        (cond
         (map? v) v
         :else (vertice v))
        old-vertices (get g :vs)]
    (graph (conj old-vertices vertice))))

(defn find-in-vertices [vs name]
  (let [f (first vs)
        r (rest vs)]
    (cond
     (= f nil) nil
     (= (get f :name) name) f
     :else (find-in-vertices r name))))
  
(defn find-vertice [g name]
  (find-in-vertices (get g :vs) name))

(defn replace-vertice [g v1 v2]
  (let [vs (get g :vs)]
    (println "try to remove" v1)
    (clojure.pprint/pprint
     (difference vs v1))
    (graph
     (conj (difference vs v1) v2))))

(def commands
  [:vultures :lions :eagles :beavers
   :tigers :skunks])

(def games
  [[:vultures :lions]
   [:vultures :eagles]
   [:lions :beavers]
   [:lions :skunks]
   [:tigers :eagles]
   [:tigers :skunks]])

(def init-graph
  (reduce add-vertice (graph) commands))

(clojure.pprint/pprint
 (replace-vertice init-graph (find-vertice init-graph :tigers)
                  (vertice :tigers #{:lions})))
