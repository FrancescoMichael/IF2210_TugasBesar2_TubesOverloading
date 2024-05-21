package card.item;
import card.creature.*;
import exceptionkerajaan.*;
// import card.UsableCard;
// import card.creature.Animals;
// import card.creature.Creature;
// import card.creature.Plant;
// import exceptionkerajaan.*;
// public class Item extends Card implements Item, UsableCard{
//     public Accelerate(){
//         // super("Accelerate",0,);
//     }
//     public void useEffect(Creature targetCard, int row, int col) throws BaseException{
//         if (targetCard instanceof Animals){
//             // targetCard.increaseWeight();
//         } else if (targetCard instanceof Plant){
//             //
//         } else {
//             throw new InvalidCardPlacementException();
//         }
//     }
import exceptionkerajaan.InvalidCardPlacementException;



//     @Override
//     public void useCard(Card targetCard, int row, int col) throws BaseException{
//         // accelerate will check if target card is Creature
//         if (targetCard instanceof Creature){
//             this.useEffect( (Creature)targetCard, row, col);
//         }
//     }
    
// }



public class ConcreteAccelerate implements ItemEffect {
    public void useEffect(Creature currentCard,Creature cardTarget, int row, int col) throws BaseException{
        if (cardTarget instanceof Animals){
            // increase age of animals
            cardTarget.increasweightAfterEffect(8);
        } else if (cardTarget instanceof Plant){
            // increase age of plant
            cardTarget.increaseWeight(2);
        } else {
            throw new InvalidCardPlacementException();
        }
    }
}