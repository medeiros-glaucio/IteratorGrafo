package grafo.dirigido;

import java.util.*;

public class BfsIterator<T> implements Iterator{
	private List<Aresta<T>> arestas;
	private List<Vertice<T>> vertices;
	private Vertice<T> raiz;
	private Deque<Vertice<T>> fila;
	
	public BfsIterator(List<Vertice<T>> vertices, List<Aresta<T>> arestas, T carga) {
		this.arestas = arestas;
		this.vertices = vertices;
		this.fila = new ArrayDeque<>();
		this.BFS(carga);
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
		raiz = fila.pollFirst();
		return raiz;
	}		
	
	
		
	private Vertice<T> getVertice(T carga){		
		for (Vertice<T> w : vertices) {
            if (w.getCarga().equals(carga)) 
                return w;
        }
        return null;        
	}
		
	private boolean exists(Vertice<T> w) {
		for (Vertice<T> x : vertices) {
			if (x.getCarga().equals(w.getCarga()))
				return true;
		}
		return false;
	}
	
	/**
	 * @return the list of all valid destinations for origin to destiny.
	 */
	
	// @SuppressWarnings("unchecked")
	public List<Vertice<T>> getIncidentes(Vertice<T> v)	{
		List<Vertice<T>> listaVertices = new ArrayList<Vertice<T>>();
				
		for( Aresta<T> a : arestas){
			if(a.getDestino().equals(v))
				listaVertices.add( (Vertice<T>) a.getOrigem() );				
			else if(a.getOrigem().equals(v))
				listaVertices.add( (Vertice<T>) a.getDestino() );
		}		
		return listaVertices;
	}	
	
	public void BFS( T source ){
		Queue<Vertice<T>> q = new LinkedList<Vertice<T>>();
		List<Vertice<T>> uAdjacentes = null;
		
		Vertice<T> v = getVertice(source);
					
		if( !exists(v) )
			return;
		// Marcando todos os nós como NAO-VISITADOS
		for(int i=0; i < vertices.size(); i++ ){
			vertices.get(i).setStatus(VertexState.Unvisited);
		}		
		
		v.setStatus(VertexState.Visited);
		q.add(v);		
	
		while ( !q.isEmpty()){
			Vertice<T> y = q.remove();
			// System.out.print("\t" + q.toString() + "\n");
			
			uAdjacentes = getIncidentes(y);
			
			for(Vertice<T> w: uAdjacentes){
				
				if( w.getStatus() == VertexState.Unvisited ) {							
					w.setStatus(VertexState.Visited);
					q.add(w);								
				}
				//showMarked();
				//System.out.print("\t" + q.toString() + "\n");
			}
			
			y.setStatus(VertexState.Finished);
			addFila(y);
		}
	}
	
	private void addFila(Vertice<T> carga) {
		if(!this.fila.contains(carga)) 
			this.fila.add(carga);
	}

}
