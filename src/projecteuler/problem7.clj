;; By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13,
;; we can see that the 6th prime is 13.

;; What is the 10 001st prime number?

;; simple, iterative approach
(defn first-n-primes
  "Return the first n prime numbers"
  [n]
  (loop [i 2 primes []]
    (if (<= n (count primes))
      primes
      (if (not-any? #(= 0 (rem i %)) primes)
        (recur (inc i) (conj primes i))
        (recur (inc i) primes)))))

(defn range-plus-2
  [start]
  (range (+ 2 start) Double/POSITIVE_INFINITY 2))

(defn next-prime
  [primes]
  (cond (empty? primes)
        2

        (= primes [2])
        3

        :else
        (first (remove (some #(= 0 (rem x %)) primes)
                       (range-plus-2 (apply max primes))))))

;; infinte lazy sequence
(defn primes
  ([] (primes '(2)))
  ([s] (cons (first s) (lazy-seq (primes (conj s (next-prime s)))))))

;; Jake's infinte lazy sequence
(defn first-n-primes
  "Return the first n prime numbers"
  [n]
  (letfn
      [(multiple-of? [a b] (= 0 (rem a b)))
       (primes
         ([] (primes (nthrest (range) 2)))
         ([s]
            (let [f (first s)]
              (cons f
                    (lazy-seq (primes (remove #(multiple-of? % f)
                                              (rest s))))))))]
    (take n (primes))))

;; Variant of Jake's infinte lazy sequence
(defn first-n-primes
  "Return the first n prime numbers"
  [n]
  (letfn [(remove-multiples [p s] (remove #(= 0 (rem % p)) s))
          (primes
            ([] (primes (nthrest (range) 2)))
            ([s]
               (let [f (first s)]
                 (cons
                  f (lazy-seq
                     (primes
                      (remove-multiples f (rest s))))))))]
    (take n (primes))))

(last (first-n-primes 10001))
