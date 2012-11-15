;; The prime factors of 13195 are 5, 7, 13 and 29.
;;
;; What is the largest prime factor of the number 600851475143 ?

(defn prime-factors
  "Return a sequence of the prime factors of n"
  [n]
  {:pre [(integer? n)]
   :post [(= n (apply * %))]}
  (loop [i 2 x n factors []]
    (if (> i x)
      (if (empty? factors) [n] factors)
      (if (= 0 (rem x i))
        (recur i (quot x i) (conj factors i))
        (recur (inc i) x factors)))))

(apply max (prime-factors 600851475143))
