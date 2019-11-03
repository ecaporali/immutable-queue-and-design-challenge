package com.ecaporali.assessment.task1

import org.specs2.mutable.Specification

class QueueTest extends Specification {

  "QueueTest" should {
    "enQueue - add element to the end of the queue" in {
      val queue: Queue[Int] = buildImmutableQueue(5)
      queue.enQueue(6) must beEqualTo(Queue.apply[Int](1, 2, 3, 4, 5, 6))
    }

    "deQueue - remove element from the beginning of the queue if queue is not empty" in {
      val queue: Queue[Int] = buildImmutableQueue(5)
      queue.deQueue must beEqualTo(Queue.apply[Int](2, 3, 4, 5))
    }

    "deQueue - throws exception if queue is empty" in {
      val queue: Queue[Int] = buildEmptyImmutableQueue
      queue.deQueue must throwA[NoSuchElementException]("queue already empty")
    }

    "isEmpty - assert queue size if it has elements" in {
      val queue: Queue[Int] = buildEmptyImmutableQueue
      queue.isEmpty must beTrue
    }

    "first - return Some containing the first element if queue is not empty" in {
      val queue: Queue[Int] = buildImmutableQueue(1)
      queue.head must beSome(1)
    }

    "first - return None if queue is empty" in {
      val queue: Queue[Int] = buildEmptyImmutableQueue
      queue.head must beNone
    }

    "apply - creates a new ImmutableQueue with the elements passed to xs" in {
      Queue.apply[Int](1, 2, 3, 4, 5) must beEqualTo(buildImmutableQueue(5))
    }

    "empty - creates an empty new ImmutableQueue" in {
      Queue.empty[Int] must beEqualTo(buildEmptyImmutableQueue)
    }
  }

  private def buildImmutableQueue(howMany: Int): Queue[Int] = ImmutableQueue.apply[Int](1 to howMany: _*)
  private def buildEmptyImmutableQueue: Queue[Int] = ImmutableQueue.apply[Int]()
}
