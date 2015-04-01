List(1,1).forall(_ == 1)

val s = List(1,1)
val s1 = List(1,3)

val ll = List(List(1,1))

ll.exists(_.sorted.zipWithIndex.forall{case (v,i) => v == s1(i)})

ll.map(_.sorted)
s1.zipWithIndex

List(3,1).sorted.zipWithIndex.forall{case (v,i) => v == s1(i)}


def appendIfNew(target: List[List[Int]], source: List[Int]): List[List[Int]] = {

  val s = source.sorted
  val exists = target.exists(l =>
    if(l.length == s.length)
      l.sorted.zipWithIndex.forall({case (v,i) => v == s(i)})
    else false
  )

  if(!exists) source +: target
  else target

}

appendIfNew(ll, s1)

Int.MaxValue > 0