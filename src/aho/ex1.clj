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

(defn replace-vertice [g v2]
  (let [vs (get g :vs)]
    (graph
     (let [name (get v2 :name)]
       (conj
         (filter #(not= (get % :name) name) vs)
         v2)))))

(defn vertice-add-edge [v e]
  (assoc v :edges (conj (get v :edges) e)))

(defn add-edge [g games]
  (let [[e1 e2] games
        v1 (find-vertice g e1)
        v2 (find-vertice g e2)
        v1n (vertice-add-edge v1 e2)
        v2n (vertice-add-edge v2 e1)]
    (reduce replace-vertice g [v1n v2n])))



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

(def with-edges
  (reduce add-edge init-graph games))

(clojure.pprint/pprint with-edges)
(clojure.pprint/pprint
 (replace-vertice init-graph (vertice :tigers #{:lions})))
