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
   (= to_sort []) []
   :else
    (let [pivot (first to_sort)
          arr (rest to_sort)
          [less more _] (reduce qsort_reduce [[] [] pivot] arr)]
      (concat (qsort less) [pivot] (qsort more)))))

(let [arr [1 9 3 4 32 45 9 1123 992 123 123 123 123 123]]
  (qsort arr))
