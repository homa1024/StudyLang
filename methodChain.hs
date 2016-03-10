-- method chain
(#) :: (a -> b) -> (b -> c) -> (a -> c)
f # g = g . f
infixl 2 #

(#>) :: a -> (a -> b) -> b
x #> f = f x
infixl 1 #>

tes5 = [1,2,3,4,5,6]
       #> domap
       where
         domap = map (*2)
                 # map (+1)
tes6 = [1,2,3,4,5,6]
       #> map (*2)
       #> map (*3)
isEven = map $ (`mod` 2) # (== 0)

removeEmpties = filter (\xs -> length xs /= 0) 
main = readFile "input.txt"
       >>= process 
       # putStr
  where
    process = lines # removeEmpties # unlines

class Addable a where
  add :: a -> a -> a
data MyData = AAA | BBB | CCC
                          deriving Show

instance Addable MyData where
  add x y = CCC
