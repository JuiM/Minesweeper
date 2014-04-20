
public class Minefield {

	int[][] field;
	boolean[][] visible;
	boolean dead = false;

	public Minefield() {
		field = new int[40][40];
		visible= new boolean[40][40];
		reset();
	}

	public void reset(){
		for(int i=0 ; i < 100 ; i++){
			int x = (int) (Math.random()*40);
			int y = (int) (Math.random()*40);
			markAsMine(x,y);
		}

		for(int x= 0; x <40; x++){
			for(int y = 0 ; y < 40 ; y++){
				if(! isMine(x,y)){
					field[x][y] = countMines(x,y);
				}
			}
		}
	}

	public boolean isMine(int x, int y){
		return field[x][y] == 9;
	}

	//count the number of adjacent spots
	//remember that the y values are inverted
	public int countMines(int x, int y){
		int count = mines(x-1, y-1) + mines(x, y-1)  +  mines(x+1, y-1)
				+ mines(x-1, y) + mines(x+1, y) + mines(x-1,y+1) + mines(x, y+1)
				+ mines(x+1, y+1);
		return count;

	}
	// checking whether valid
	public int mines(int x, int y){
		if( x >= 0 && x < 40 && y>= 0 && y < 40 && isMine(x,y)) {
			return 1;
		}
		else{
			return 0;
		}
	}

	public boolean cleared(){
		return false;
	}

	public int getSquare(int x, int y){
		return field[x][y];
	}

	public void markAsFlag(int x, int y){
		field[x][y] = -1;
	}
	public void markAsMine(int x, int y){
		field[x][y] = 9;
	}

	public boolean isFlag(int x, int y) {
		return field[x][y] == -1;
	}

	public boolean isVisible(int x, int y){
		return visible[x][y];
	}
	public void clicked(int x, int y) {

		visible[x][y-1] = true;
		if(x>=0 && x<40 && y >= 0 && y <40){
			if(field[x][y] == 0){
				if(!visible[x+1][y]){
					clicked(x+1, y);
				}
				if(! visible[x-1][y]){
					clicked(x-1, y);
				}
				if(! visible[x][y+1]){
					clicked(x,y+1);
				}
				if(! visible[x][y-1]){
					clicked(x, y-1);
				}
			}
			// what else needs to become visible - the island
		}
	}

}
