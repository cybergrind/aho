(ns aho.sort)


(defn qsort_reduce
  [[less more pivot_val] val]
   (cond
    (<= val pivot_val) [(conj less val) more pivot_val]
    (> val pivot_val) [less (conj more val) pivot_val]))

(qsort_reduce
 [[1] [3] 2] 5)

(defn qsort
  [to_sort]
  (cond
   (<= 1 (count to_sort)) to_sort
   :else
    (let [pivot (first to_sort)
          arr (rest to_sort)
          [less more _] (reduce qsort_reduce [[] [] pivot] arr)]
      (concat (qsort less) [pivot] (qsort more)))))

(let [arr [1 9 3 4 32 45 9 1123 992 123 123 123 123 123]]
  (qsort arr))


(defn msort
  [to_sort]
  (println "to_sort: " to_sort)
  (cond
   (<= (count to_sort) 1) to_sort
   :else
   (let [med (quot (count to_sort) 2)
         v1 (msort (subvec to_sort 0 med))
         v2 (msort (subvec to_sort med))]
     (cond
      (<= (last v1) (first v2)) (concat v1 v2)
      :else (mmerge v1 v2 [])))))

(defn mmerge
  [v1 v2 result]
  (println "mmerge: " v1 " " v2 " " result)
  (cond
   (= v1 []) (concat result v2)
   (= v2 []) (concat result v1)
   :else
   (let [fv1 (first v1)
         fv2 (first v2)]
     (cond
      (< fv1 fv2) (mmerge (rest v1) v2 (conj result fv1))
      (> fv1 fv2) (mmerge v1 (rest v2) (conj result fv2))
      :else (mmerge (rest v1) (rest v2) (conj result fv1 fv2))))))

(let [arr [1 9 3 4 32 45 9 1123 992 123 123 123 123 123]]
  (msort arr))
