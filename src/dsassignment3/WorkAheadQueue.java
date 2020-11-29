/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsassignment3;

import java.util.ArrayList;

/**
 *
 * @author prabathrai21
 */
public class WorkAheadQueue<T> implements WorkAheadQueueADT<T> {
    protected LinearNode<T> front;
    protected LinearNode<T> back;
    protected ArrayList<LinearNode<T>> frontThreeNodes;
    protected ArrayList<T> frontThreeElements;
    protected int numNodes;
    
    public WorkAheadQueue(){
        front = null;
        back = null;
        numNodes = 0;
        frontThreeElements = new ArrayList<>();
        frontThreeNodes = new ArrayList<>();
    }
    /**
     * Removes and returns the element that is at place x in the queue.
     * Precondition: x must be less than 3, x must be less than size
     * Note: indexing from 0: 0 == front element, 1 == second element, etc.
     * @param x the passed in index of the element to be removed
     * @return the element removed from the queue
     * @throws EmptyCollectionException if the queue is empty
     * @throws InvalidArgumentException if x > 2, or x > size of collection
     * 
     */

    @Override
    public T dequeue(int x) throws EmptyCollectionException, InvalidArgumentException {
        LinearNode<T> temp =  front;
        if(numNodes == 0){
            throw new EmptyCollectionException("Empty");
        }
        if(x > 2 || x > numNodes){
            throw new InvalidArgumentException("invalid");
        }
        if( x == 0 && numNodes > 0){
            front = front.getNext();
            front.setPrev(null);
        }
        else if(x == 2 && numNodes >= 2){
            temp = front.getNext().getNext();
            temp.getPrev().setNext(temp.getNext());
            temp.getNext().setPrev(temp.getPrev());
        }
        else if ( x == 1 && numNodes >= 1){
           temp = front.getNext();
            temp.getPrev().setNext(temp.getNext());
            temp.getNext().setPrev(temp.getPrev());
         
            
        }
        numNodes--;
        firstThreeElements();
        firstThreeNodes();
        return temp.getElement();
        
    }
    /**
     * Returns (without removing) the element that is at place x in the queue.
     * Precondition: x must be less than 3, x must be less than size
     * Note: indexing from 0: 0 == front element, 1 == second element, etc.
     * @return the element at the front of the queue
     * @throws EmptyCollectionException if the queue is empty
     * @throws InvalidArgumentException if x > 2, or x > size of collection
     * @param x the specified index of the element to return
     */

    @Override
    public T first(int x) throws EmptyCollectionException, InvalidArgumentException {
     LinearNode<T> temp =  front;
        if(numNodes == 0){
            throw new EmptyCollectionException("Empty");
        }
        if(x > 2 || x >= numNodes){
            throw new InvalidArgumentException("invalid");
       }
    
        if(x == 2 && numNodes >= 2){
            temp = front.getNext().getNext();
           
        }
        else if ( x == 1 && numNodes >= 1){
           temp = front.getNext();
        }
        return temp.getElement();
    }
/**
     * Returns an ArrayList of the first three nodes in the queue.
     * @return ArrayList<LinearNode<T>> array list of nodes
     * @throws EmptyCollectionException if the queue is empty
     */
    @Override
    public ArrayList<LinearNode<T>> firstThreeNodes() throws EmptyCollectionException {
        frontThreeNodes.clear();
       if(numNodes == 0){
            throw new EmptyCollectionException("Empty");
        } else{
           LinearNode<T> temp = front;
           for(int rai = 0; rai < 3 && rai < size(); rai++){
               frontThreeNodes.add(rai, temp);
               temp = temp.getNext();
           } 
       } return frontThreeNodes;
       
    }
    
    /**
     * Returns an ArrayList of the first three elements in the queue.
     * @return ArrayList<T> array list of elements
     * @throws EmptyCollectionException if the queue is empty
     */

    @Override
    public ArrayList<T> firstThreeElements() throws EmptyCollectionException {
        frontThreeElements.clear();
        if(numNodes == 0){
            throw new EmptyCollectionException("Empty");
        } 
        LinearNode<T> temp = front;
           
           for(int rai = 0; rai < 3 && rai < size(); rai++){
               frontThreeElements.add(temp.getElement());
               temp = temp.getNext();
        }
        
       return frontThreeElements;
    }

    @Override
    public void enqueue(T element) {
        LinearNode temp = new LinearNode(element);
        if(numNodes == 0){
            front = temp;
            back = temp;
        } else{
            back.setNext(temp);
            back = temp;
            
        }
        numNodes++;
    }

    @Override
    public T dequeue() throws EmptyCollectionException {
        if(numNodes == 0){
            throw new EmptyCollectionException("Empty");
        }
        LinearNode<T> temp = front;
        front = front.getNext();
        numNodes--;
        return temp.getElement();
    }

    @Override
    public T first() throws EmptyCollectionException {
        if(numNodes == 0){
            throw new EmptyCollectionException("Empty");
        }
        return front.getElement();
    }

    @Override
    public boolean isEmpty() {
        return numNodes == 0;
    }

    @Override
    public int size() {
        return numNodes;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        LinearNode curr = front;
        for (int i = 0; i < size(); i++) {
            sb.append(curr.getElement().toString());
            if (i < size() - 1) {
                sb.append(", ");
            }curr = curr.getNext();
        }return sb.toString();}
    
}
