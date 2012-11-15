(defn naturals
  ([] (naturals 1))
  ([n] (cons n (lazy-seq (naturals (inc n))))))
