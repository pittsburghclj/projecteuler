(defn square "Returns the square of x" [x] (* x x))

(defn pythagorean-triplet?
  "A Pythagorean triplet is a set of three natural numbers,
a < b < c, for which, a*a + b*b = c*c.

For example, 3*3 + 4*4 = 9 + 16 = 25 = 5*5."
  [a b c]
  (and (integer? a)
       (integer? b)
       (integer? c)
       (< 0 a b c)
       (= (+ (square a) (square b)) (square c))))

(defn pythagorean-triplets-summing-to
  "Returns a sequence of every Pythagorean triplet a,b,c where a+b+c=sum."
  [sum]
  {:pre [(< 0 sum) (integer? sum)]}
  (for [a (range 1 (quot sum 3))
        b (range (inc a) (quot (inc (- sum a)) 2))
        c (list (- sum a b))
        :when (pythagorean-triplet? a b c)]
    [a b c]))

(defn problem-9
  "http://projecteuler.net/problem=9
There exists exactly one Pythagorean triplet for which a + b + c = 1000.
Find the product a*b*c."
  []
  (let [[a b c] (first (pythagorean-triplets-summing-to 1000))]
    (* a b c)))
