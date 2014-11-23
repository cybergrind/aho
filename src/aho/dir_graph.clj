(ns aho.dir_graph)


(def test_graph
  [{:v :a, :e [:b :d :e]}
   {:v :b, :e [:c]}
   {:v :c, :e [:f]},
   {:v :f, :e [:h :g]},
   {:v :h, :e [:g]},
   {:v :g, :e [:h]},
   {:v :d, :e []}
   {:v :e, :e [:g]}])

(clojure.pprint/pprint (sort-by #(get %1 :v) test_graph))

(defn numbered_vertices [graph]
  (let [sorted_vertices
        (sort (map #(get %1 :v) graph))]
    (zipmap sorted_vertices (iterate inc 0))))

(defn numbered_graph [graph]
  (let [num_v (numbered_vertices graph)]
    (map #(assoc %1 :num (get num_v (get %1 :v)))
         graph)))

(clojure.pprint/pprint (numbered_graph test_graph))


(defn mark-if [vertice label mark]
  (cond
   (= label (get vertice :v)) (assoc vertice :mark mark)
   :else vertice))

(defn check-mark [vertice mark]
  (= mark (get vertice :mark)))

(defn mark [graph label mark]
  (map #(mark-if %1 label mark) graph))

(defn get-label [vertice] (get vertice :v))

(defn find-by-label [label graph]
  (first (filter #(= label (get-label %1)) graph)))

(defn get-subvertices [vertice graph]
  (map #(find-by-label %1 graph) (get vertice :e)))

(defn get-sublabels [vertice graph]
  (get vertice :e))

(defn topsort
  [graph label]
  (let [vertice (find-by-label label graph)]
    (cond
     (not (check-mark vertice :visited))
     (let [new_graph (mark graph (get-label vertice) :visited)
           ret_graph (reduce topsort new_graph (get-sublabels vertice new_graph))]
       (println vertice)
       ret_graph)
     :else graph)))

(clojure.pprint/pprint (topsort test_graph :a))
  
; return matrix of graph filled with 0
(defn null_matrix [graph]
  (let [
