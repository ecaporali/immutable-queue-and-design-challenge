package com.ecaporali.assessment.task1

import scala.collection.mutable.ListBuffer

sealed trait Queue[T] {
  def isEmpty: Boolean
  def enQueue(t: T): Queue[T]
  def deQueue(): Queue[T]
  def head: Option[T]
}

/*
  Implementation of ImmutableQueue backed by a ListBuffer for constant add time to end of queue and
  constant remove time from beginning of queue.
  - enQueue runs in O(1): addOne(t) is executed in constant time because it keeps a pointer of the last element
  - deQueue runs in O(1): remove(0) is executed in constant time because it runs .tail on its internal list
 */
final class ImmutableQueue[T] private (private val xs: ListBuffer[T]) extends Queue[T] {
  override def isEmpty: Boolean = xs.isEmpty
  override def enQueue(t: T): Queue[T] = new ImmutableQueue[T](xs.addOne(t))
  override def deQueue(): Queue[T] = if (!isEmpty) new ImmutableQueue({xs.remove(0); xs}) else throw new NoSuchElementException("queue already empty")
  override def head: Option[T] = xs.headOption

  override def equals(obj: Any): Boolean = xs.equals(obj.asInstanceOf[ImmutableQueue[T]].xs)
  override def hashCode(): Int = xs.hashCode()
}

object ImmutableQueue {
  def apply[T](xs: T*): Queue[T] = new ImmutableQueue[T](ListBuffer.from[T](xs))
  def empty[T]: Queue[T] = new ImmutableQueue[T](ListBuffer.empty[T])
}

object Queue {
  def apply[T](xs: T*): Queue[T] = ImmutableQueue.apply[T](xs:_*)
  def empty[T]: Queue[T] = ImmutableQueue.empty[T]
}
