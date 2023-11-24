package model;


import read.ToJsonReader;

public class Control {


    private Graph<String> grafo;
    private GraphM<String> grafoM;


    public Control()throws Exception{
        grafo = new Graph<>();
        grafoM = new GraphM<>();
    }

    public Graph<String> getGrafo() {
        return grafo;
    }

    public void setGrafo(Graph<String> grafo) {
        this.grafo = grafo;
    }

    public GraphM<String> getGrafoM() {
        return grafoM;
    }

    public void setGrafoM(GraphM<String> grafoM) {
        this.grafoM = grafoM;
    }

    public void addVertex(String value, int key)throws Exception{
        grafo.addVertex(value,key);
    }

    public String proveConex(){
        String out = "";
        int count = grafo.proveConex();
        int real = grafo.getHashSize();
        if(count == real){
            out = "Is strongly conex";
        }else {
            out= "Is not strongly conex";
        }
        return out;


    }

    public String hackingFromGuard(int graph){
        if(graph == 1){
                return grafo.dijkstraBase(0, 47);
        }else{
                return grafoM.dijkstraBase(0, 47);
            }
    }

    public String hackingFromTo(int graph, int s, int f){
        if(graph==1){
            return grafo.dijkstraBase(s, f);
        }else{
            return grafoM.dijkstraBase(s, f);
        }
    }


    public String proveFinish(int graph, int v, int f){

        if(graph == 1){
            grafo.BFS(v);
            if(grafo.prove(f)){
                return "Good you still can hacking the President";
            }else{
                return "No, sorry hacker you need to back to another net ";
            }
        }else{
            grafoM.BFS(v);
            if(grafoM.prove(f)){
                return "you can hacking the president";
            }else{
                return "No, sorry hacker you need to back to another net ";
            }
        }
    }

    public void loadVertexDataBase() {
        if(ToJsonReader.readV() != null) {
            for(int i = 0; i < ToJsonReader.readV().size(); i++) {
                grafo.vertexes.put(ToJsonReader.readV().get(i).getKey(), ToJsonReader.readV().get(i));
                grafoM.addVertex(ToJsonReader.readV().get(i).getValue(), ToJsonReader.readV().get(i).getKey());
            }
        } else {
            System.out.println("the database are empty.");
        }
        grafo.initialize();
    }

    public void loadEdgeDataBase() throws Exception {

        if(ToJsonReader.readA() != null) {

            for(int i = 0; i < ToJsonReader.readA().size(); i++) {
                grafo.addArista(ToJsonReader.readA().get(i).getFrom(), ToJsonReader.readA().get(i).getTo(), ToJsonReader.readA().get(i).getWeight());
                grafoM.addEdge(ToJsonReader.readA().get(i).getFrom(), ToJsonReader.readA().get(i).getTo(), ToJsonReader.readA().get(i).getWeight());
            }

        } else {

            System.out.println("the database are empty.");

        }
    }

}
