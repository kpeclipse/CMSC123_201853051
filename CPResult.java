import java.util.LinkedList;

class CPResult {
    LinkedList<String> criticalPath;
    double criticalTime;

    public CPResult(LinkedList<String> CP, double CT){
        criticalPath = CP;
        criticalTime = CT;
    }
}