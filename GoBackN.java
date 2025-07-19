import java.util.*;
class GBNSender {
private int windowSize = 4;
private int totalPackets = 10;
private int base = 0;
private int nextSeqNum = 0;
public void sendPackets() {
while (base < totalPackets) {
// Send all packets in window
while (nextSeqNum < base + windowSize && nextSeqNum < totalPackets) {
System.out.println("Sender: Sending Packet " + nextSeqNum);
nextSeqNum++;
}
// Simulate ACK
boolean ack = Math.random() > 0.3; // 70% chance ACK is received
if (ack) {
System.out.println("Sender: ACK received for Packet " + base + "\n");
base++;
} else {
System.out.println("Sender: Timeout! Resending from Packet " + base + "\n");
nextSeqNum = base; // Go-Back-N resend from base
}
}
}
}
class GBNReceiver {
// Not needed in simulation; logic is part of sender loop
}
public class GoBackN {
public static void main(String[] args) {
GBNSender sender = new GBNSender();
sender.sendPackets();
}
}