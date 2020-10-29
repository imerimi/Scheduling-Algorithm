import java.util.*; 

public class STRNwithPrior{
	public static void main(String[] args){
		
		boolean range=true;
		int totalTime=0;
		int numState=0;
		ArrayList<Process> processes=new ArrayList<>();
		
		Scanner input= new Scanner(System.in);
		
		while(range){
			System.out.println("How many state(s) are there?");
			if (input.hasNextInt()) {
		    	numState= input.nextInt();
			}
			if (numState<3 || numState>10){
				System.out.println("Enter 3-10 only.");
			}
			else{
				range=false;
			}
		}
		
		for(int i=0; i<numState;i++){
			String name= "P"+i;
			
			System.out.println("Arrival time for P"+i+"?");
			int arrivalTime=input.nextInt();
			
			System.out.println("Burst time for P"+i+"?");
			int burstTime=input.nextInt();
			totalTime+=burstTime;
			
			System.out.println("Priority for P"+i+"?");
			int priority=input.nextInt();
			
			processes.add(new Process(name,arrivalTime,burstTime,priority,0));
		}
		
		Collections.sort(processes, new ComparatorByArrivalTimeandPriority());
		
		System.out.println();
		System.out.println("Process->Arrival Time-> BurstTime-> Priority");
		for(Process p:processes){
			System.out.println(p);
	   }
		
		//End of getting user input 
		
		
		//--------------------------------------------------------------------------------------------------
		int timeLine=processes.get(0).getArrivalTime();
		int firstArrivalTime=processes.get(0).getArrivalTime();
		ArrayList<Process> entered=new ArrayList<>();
		ArrayList<Process> notEntered=new ArrayList<>();
		
		System.out.println();
		System.out.print(firstArrivalTime+" | ");
		for(int i=firstArrivalTime;i<=totalTime+firstArrivalTime;i++){
			
			for(int j=0;j<numState;j++){    //put the process that had arrived in entered.
				if(processes.get(j).getArrivalTime()<=timeLine && processes.get(j).getBurstTime()!=0){ 
					if(!entered.contains(processes.get(j))){
						entered.add(processes.get(j));
					}
				}
			}
			
			for(int k=0;k<entered.size();k++){         //remove the process which burstTime=0
				if(entered.get(k).getBurstTime()==0){
					entered.remove(k);
				}
			}
			
			Collections.sort(entered, new ComparatorByBurstTimeandPriority());//sort by burstTime then Priority
			
			
			if(entered.size()>=2){ //same bursttime and priority? let the one who havent been used enter to enter first.
				if(entered.get(0).getBurstTime()== entered.get(1).getBurstTime()){
					if(entered.get(0).getPriority()== entered.get(1).getPriority()){
						if(entered.get(0).getUsed()==1 && entered.get(1).getUsed()==0){
							entered.remove(0);
						}
					}
				}
			}

			timeLine+=1;
			if(entered.size()!=0){
				System.out.print(entered.get(0).getName()+" | ");
				entered.get(0).setBurstTime(entered.get(0).getBurstTime()-1);
			}
			System.out.print(timeLine+" | "); //end time
			if(timeLine==totalTime+firstArrivalTime){
				break;
			}
		}
		
	}
}


class Process implements Comparable<Process>{ 
	
	private String name;
	private int arrivalTime;
	private int burstTime;
	private int priority;
	private int used;
	
	public Process(String name,int arrivalTime, int burstTime, int priority,int used){
		this.name=name;
		this.arrivalTime=arrivalTime;
		this.burstTime=burstTime;
		this.priority=priority;
		this.used=used;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	public int getArrivalTime(){
		return arrivalTime;
	}
	public void setArrivalTime(int arrivalTime){
		this.arrivalTime=arrivalTime;
	}
	public int getBurstTime(){
		return burstTime;
	}
	public void setBurstTime(int burstTime){
		this.burstTime=burstTime;
	}
	public int getPriority(){
		return priority;
	}
	public void setPriority(int priority){
		this.priority=priority;
	}
	public int getUsed(){
		return used;
	}
	public void setUsed(int used){
		this.used=used;
	}
	public String toString(){
		return name+"->"+arrivalTime+"->"+burstTime+"->"+priority;
	}
	
	@Override
	public int compareTo(Process p) {
        int cmp1 = ((Process)p).getArrivalTime();
		return this.arrivalTime - cmp1;
    }
}

class ComparatorByArrivalTimeandPriority implements Comparator<Process> {

    @Override
    public int compare(Process p1, Process p2) {
        int cmp2 = p1.getArrivalTime() - p2.getArrivalTime();
        if(cmp2==0){
			cmp2 = Integer.compare(p1.getPriority(),p2.getPriority());
        }
		return cmp2;
    }

}

class ComparatorByBurstTimeandPriority implements Comparator<Process> {

    @Override
    public int compare(Process p1, Process p2) {
        int cmp3 = p1.getBurstTime() - p2.getBurstTime();
		if(cmp3==0){
			cmp3 = Integer.compare(p1.getPriority(),p2.getPriority());
        }
		return cmp3;
    }

}