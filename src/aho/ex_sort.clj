(ns aho.sort)


(defn qsort
  [to_sort]
  (cond
   (= to_sort []) []
   :else
    (let [pivot (first to_sort)
          arr (rest to_sort)
          less (filter #(<= % pivot) arr)
          more (filter #(> % pivot) arr)
          ]
      (concat (qsort less) [pivot] (qsort more)))))

(let [arr [1 9 3 4 32 45 9 1123 992 123 123 123 123 123]]
  (qsort arr))
