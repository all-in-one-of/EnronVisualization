package org.spiffy.enron

/** A counter of the amount of directional e-mail activity between two people.
  * @constructor
  * @param sendID The personal identifier of the sender.
  * @param recvID The personal identifier of the receiver.
  * @param send The number of e-mails sent from sendID to recvID during the sample period.
  * @param recv The number of e-mails sent from recvID to sendID during the sample period.
  */
class BiTraffic private (val sendID: Long, val recvID: Long, val send: Long, val recv: Long) 
  extends Ordered[BiTraffic] 
{
  /** Ordered in increasing activity and ascending IDs. */
  def compare(that: BiTraffic): Int =
    (total compare that.total) match {
      case 0 => 
        (sendID compare that.sendID) match {
          case 0 => recvID compare that.recvID
          case d => d
        }
      case c => c
    }
  
  /** Total number of emails sent in both directions. */ 
  def total = send + recv
  
  override def toString = "BiTraffic(sendID=" + sendID + ", recvID=" + recvID + ", send=" + send + ", recv=" + recv + ")"
}

object BiTraffic {
  def apply(sendID: Long, recvID: Long) = new BiTraffic(sendID, recvID, 0L, 0L)
  def apply(sendID: Long, recvID: Long, send: Long, recv: Long) = new BiTraffic(sendID, recvID, send, recv)
}
