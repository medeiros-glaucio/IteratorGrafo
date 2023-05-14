package grafo.dirigido;

import java.util.*;

public class DfsIterator<T> implements Iterator{
	private List<Vertice<T>> vertices;
    private List<Aresta<T>> arestas;
    private Deque<Vertice<T>> fila;
    private Vertice<T> raiz;

	public DfsIterator(List<Vertice<T>> vertices, List<Aresta<T>> arestas, T carga) {
		this.vertices = vertices;
		this.arestas = arestas;
		this.fila = new ArrayDeque<>();
		this.DFS(carga);
		
	}
	
	@Override
	public boolean hasNext() {
		return !fila.isEmpty();
	}

	@Override
	public Vertice<T> next() {
		if (!hasNext()) {
			throw new IllegalStateException("Não há mais elementos para iterar");
		}
		raiz = fila.pollLast();
		return raiz;
	}
	
	public void DFS(T source){
		Vertice<T> u = null;
				
		if ((u = getVertice(source)) == null) {
			System.err.println("vertice nao encontrado em runDFS()");
			return;
		}
		
		for(int i=0; i < vertices.size(); i++ ){
			vertices.get(i).setStatus(VertexState.Unvisited);
		}
		
		runDFS( u );
		
	}
	
	// @SuppressWarnings("unchecked")
	private void runDFS(Vertice<T> u){  // runDFS(Vertice<T> u, Grafo<T> grafo)
		Vertice<T> w = null;
		List<Aresta<T>> uAdjacentes = null;
				
		u.setStatus(VertexState.Visited);
		// grafo.addVertice( u.getCarga());
		
		uAdjacentes = u.getAdj();
			
		for(Aresta<T> arco: uAdjacentes){
			w = (Vertice<T>) arco.getDestino();
			
			if( w.getStatus() == VertexState.Unvisited )							
				runDFS(w); // runDFS(w,grafo);
			//	grafo.addAresta(  arco.getOrigem(), arco.getDestino(), arco.getPeso());								
		} 
		
		u.setStatus(VertexState.Finished);
		addFila(u);
	}
	
	public Vertice<T> getVertice( T carga){

        for (Vertice<T> u : vertices) {
            if ( u.getCarga().equals( carga ))
                return u;
        }
        return null;
    }

    private void addFila(Vertice<T> carga) {
		if(!this.fila.contains(carga)) 
			this.fila.add(carga);
	}
    
}
