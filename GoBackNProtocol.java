import java.util.Timer;
import java.util.TimerTask;
class Packet
{
	int sequenceNumber;
	String data;
	public Packet(int sequenceNumber,String data)
	{
		this.sequenceNumber=sequenceNumber;
		this.data=data;
	}
}
class Sender
{
	private int sequenceNumber=0;
	private int windowSize=4;
	private Packet[]packets;
	private Timer timer;
	public Sender(int totalPackets)
	{
		packets=new Packet[totalPackets];
		for(int i=0;i<totalPackets;i++)
		{
			packets[i]=new Packet(i,"Data for packets"+i);
		}
}
public void startSending()
	{
	int sendBase=0;
	int nextSeqNum=0;
	while(sendBase<packets.length)
		{
		for (int i=sendBase;i<Math.min(sendBase+windowSize,packets.length);i++)
			{
			if(i>=nextSeqNum)
				{
				sendPacket(packets[i]);
				nextSeqNum++;
				if(i==sendBase)
					{
					startTimer();
					}
				}
			}
			try
			{
				Thread.sleep(3000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			for(int i=sendBase;i<nextSeqNum;i++)
			{
				sendPacket(packets[i]);
			}
		}
	}
	private void sendPacket(Packet packet)
	{
		System.out.println("Sending packet:"+packet.sequenceNumber);
	}
	private void startTimer()
	{
		if(timer!=null)
		{
			timer.cancel();
		}
		timer=new Timer();
		timer.schedule(new TimerTask()
		{
			@Override
				public void run()
			{
				System.out.println("Timeout Occured. Resending packets");
			}
		},2000);
	}
}
class Receiver
{
	public void receivePacket(Packet packet)
	{
		System.out.println("Received packet:"+packet.sequenceNumber);
	}
}
public class GoBackNProtocol
{
	public static void main(String[]args)
	{
		Sender sender=new Sender(10);
		Receiver receiver=new Receiver();
		sender.startSending();
	}
}