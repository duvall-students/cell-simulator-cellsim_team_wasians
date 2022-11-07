package model.cellObject;

abstract class SeaCreature extends CellObject{
	protected int breedingTime;
	
	abstract void step();
}

