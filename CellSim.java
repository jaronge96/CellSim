public class CellSim{

	public static void main(String[] args){
		//User inputs percent values for percentX and percentBlank
		int percentBlank ;
		int percentX ;
		int n = 10;
		char[][] tissue = new char[n][n];
		System.out.println("Enter the percent for X:");
		percentX = IO.readInt();
		System.out.println("Enter the percent for Blank:");
		percentBlank = IO.readInt();
		assignCellTypes(tissue, percentX, percentBlank);
		int row;
		int col;
		int threshold;
		System.out.println("Enter the row#, column#, and threshold percentage:");
		row = 10;
		col = 10;
		threshold = IO.readInt();
		isSatisfied(tissue, row, col, threshold);
		boardSatisfied(tissue, threshold);
	}
	/**
	 * Given a tissue sample, prints the cell make up in grid form
	 *
	 * @param tissue a 2-D character array representing a tissue sample
	 *
	 ***/
	public static void printTissue(char[][] tissue){
		int a;
		int b;
		for(a=0; a<10; a++){
			for(b=0; b<10; b++){
				tissue[a][b]='O';
				System.out.print(tissue[a][b]);
			}
			System.out.println(""); //Prints a 10x10 grid
		}
	}
	/**
	 *
	 * Phase II: Random assignment of all cells (100% credit)
	 *
	 * @param tissue a 2-D character array that has been initialized
	 * @param percentBlank the percentage of blank cells that should appear in the tissue
	 * @param percentX Of the remaining cells, not blank, the percentage of X cells that should appear in the tissue. Round up if not a whole number
	 *
	 **/
	public static void assignCellTypes(char[][] tissue, int percentBlank, int percentX){
		int a;
		int b;
		double ex=percentX*0.01;
		double blank=percentBlank*0.01;
		for(a=0; a<10; a++){
			for(b=0; b<10; b++){
				double r = Math.random(); //Generates random number between 0-1.
				if (r <blank && r>=0)
					tissue[a][b] = 'X';
				if (r <ex+blank && r>=blank)
					tissue[a][b] = ' ';
				if (r<=1 && r>=blank+ex)
					tissue[a][b]= 'O';
				System.out.print(tissue[a][b]);
			}
			System.out.println("");
		}
	}
	/**
	 * Given a tissue sample, and a (row, col) index into the array, determines if the agent at that location is satisfied.
	 * Note: Blank cells are always satisfied (as there is no agent)
	 *
	 * @param tissue a 2-D character array that has been initialized
	 * @param row the row index of the agent
	 * @param col the col index of the agent
	 * @param threshold the percentage of like agents that must surround the agent to be satisfied
	 * @return boolean indicating if given agent is satisfied
	 *
	 **/
	public static boolean isSatisfied(char[][] tissue, int row, int col, int threshold){
		int a;
		int b;
		float m=0;
		char cellType;
		char satis;
		float n=0;
		float t = threshold/100;
		boolean satisfied = false;
		for (a=0; a<(col-1); a++){
			for (b=0; b<(row-1); b++){
				cellType = tissue[a][b];
				if (cellType == 'X') //Deal with X cell first.
					// 4 Corners on Grid
					if ((a==0) && (b==0)) //Top Left Corner
						if(tissue[a+1][b]=='X'||tissue[a+1][b]=='O')
							m=m++;
				if(tissue[a+1][b]=='X')
					n=n++;
				if(tissue[a][b+1]=='X'||tissue[a][b+1]=='O')
					m=m++;
				if(tissue[a][b+1]=='X')
					n=n++;
				if(tissue[a+1][b+1]=='X'||tissue[a+1][b+1]=='O')
					m=m++;
				if(tissue[a+1][b+1]=='X')
					n=n++;
				if (m==0)
					satis='N';
				else
					if (n/m>=t)
						satis='Y';
					else
						satis='N';
				if (tissue[a][b]=='X')
					tissue[a][b]='x';
				if ((a==0) && (b==col-1)) //Top Right Corner
					if(tissue[a][b-1]=='X'||tissue[a][b-1]=='O')
						m=m++;
				if(tissue[a][b-1]=='X')
					n=n++;
				if(tissue[a+1][b]=='X'||tissue[a+1][b]=='O')
					m=m++;
				if(tissue[a+1][b]=='X')
					n=n++;
				if(tissue[a+1][b-1]=='X'||tissue[a+1][b-1]=='O')
					m=m++;
				if(tissue[a+1][b-1]=='X')
					n=n++;
				if (m==0)
					satis='N';
				else
					if (n/m>=t)
						satis='Y';
					else
						satis='N';
				if (tissue[a][b]=='X')
					tissue[a][b]='x';
				if ((a==row-1) && (b==0)) //Bottom Left Corner
					if(tissue[a][b+1]=='X'||tissue[a][b+1]=='O')
						m=m++;
				if(tissue[a][b+1]=='X')
					n=n++;
				if(tissue[a-1][b]=='X'||tissue[a-1][b]=='O')
					m=m++;
				if(tissue[a-1][b]=='X')
					n=n++;
				if(tissue[a-1][b+1]=='X'||tissue[a-1][b+1]=='O')
					m=m++;
				if(tissue[a-1][b+1]=='X')
					n=n++;
				if (m==0)
					satis='N';
				else
					if (n/m>=t)
						satis='Y';
					else
						satis='N';
				if (tissue[a][b]=='X')
					tissue[a][b]='x';
				if ((a==row-1) && (b==col-1)) //Bottom Right Corner
					if(tissue[a][b-1]=='X'||tissue[a][b-1]=='O')
						m=m++;
				if(tissue[a][b-1]=='X')
					n=n++;
				if(tissue[a-1][b]=='X'||tissue[a-1][b]=='O')
					m=m++;
				if(tissue[a-1][b]=='X')
					n=n++;
				if(tissue[a-1][b-1]=='X'||tissue[a-1][b-1]=='O')
					m=m++;
				if(tissue[a-1][b-1]=='X')
					n=n++;
				if (m==0)
					satis='N';
				else
					if (n/m>=t)
						satis='Y';
					else
						satis='N';
				if (tissue[a][b]=='X')
					tissue[a][b]='x';
				//	4 Sides of the grid disregarding corners.
				if ((a==0) && ((b>0) ||(b<col-1))) //Top row
					if(tissue[a+1][b]=='X'||tissue[a+1][b]=='O')
						m=m++;
				if(tissue[a+1][b]=='X')
					n=n++;
				if(tissue[a][b-1]=='X'||tissue[a][b-1]=='O')
					m=m++;
				if(tissue[a][b-1]=='X')
					n=n++;
				if(tissue[a+1][b+1]=='X'||tissue[a+1][b+1]=='O')
					m=m++;
				if(tissue[a+1][b+1]=='X')
					n=n++;
				if(tissue[a+1][b]=='X'||tissue[a+1][b]=='O')
					m=m++;
				if(tissue[a+1][b]=='X')
					n=n++;
				if(tissue[a][b+1]=='X'||tissue[a][b+1]=='O')
					m=m++;
				if(tissue[a][b+1]=='X')
					n=n++;
				if (m==0)
					satis='N';
				else
					if (n/m>=t)
						satis='Y';
					else
						satis='N';
				if (tissue[a][b]=='X')
					tissue[a][b]='x';
				if ((a>0)||(a<row-1)&&(b==col-1)) //Right column
					if(tissue[a][b-1]=='X'||tissue[a][b-1]=='O')
						m=m++;
				if(tissue[a][b-1]=='X')
					n=n++;
				if(tissue[a+1][b]=='X'||tissue[a+1][b]=='O')
					m=m++;
				if(tissue[a+1][b]=='X')
					n=n++;
				if(tissue[a+1][b-1]=='X'||tissue[a+1][b-1]=='O')
					m=m++;
				if(tissue[a+1][b-1]=='X')
					n=n++;
				if(tissue[a][b-1]=='X'||tissue[a][b-1]=='O')
					m=m++;
				if(tissue[a][b-1]=='X')
					n=n++;
				if(tissue[a-1][b]=='X'||tissue[a-1][b]=='O')
					m=m++;
				if(tissue[a-1][b]=='X')
					n=n++;
				if (m==0)
					satis='N';
				else
					if (n/m>=t)
						satis='Y';
					else
						satis='N';
				if (tissue[a][b]=='X')
					tissue[a][b]='x';
				if ((a==row-1) && ((b>0)||(b<col-1))) //Bottom Row
					if(tissue[a][b+1]=='X'||tissue[a][b+1]=='O')
						m=m++;
				if(tissue[a][b+1]=='X')
					n=n++;
				if(tissue[a-1][b]=='X'||tissue[a-1][b]=='O')
					m=m++;
				if(tissue[a-1][b]=='X')
					n=n++;
				if(tissue[a-1][b+1]=='X'||tissue[a-1][b+1]=='O')
					m=m++;
				if(tissue[a-1][b+1]=='X')
					n=n++;
				if(tissue[a][b-1]=='X'||tissue[a][b-1]=='O')
					m=m++;
				if(tissue[a][b-1]=='X')
					n=n++;
				if(tissue[a-1][b-1]=='X'||tissue[a-1][b-1]=='O')
					m=m++;
				if(tissue[a-1][b-1]=='X')
					n=n++;
				if (m==0)
					satis='N';
				else
					if (n/m>=t)
						satis='Y';
					else
						satis='N';
				if (tissue[a][b]=='X')
					tissue[a][b]='x';
				if ((b==0)||(a<row-1) && (a>0)) //Left column
					if(tissue[a][b+1]=='X'||tissue[a][b+1]=='O')
						m=m++;
				if(tissue[a][b+1]=='X')
					n=n++;
				if(tissue[a-1][b]=='X'||tissue[a-1][b]=='O')
					m=m++;
				if(tissue[a-1][b]=='X')
					n=n++;
				if(tissue[a-1][b+1]=='X'||tissue[a-1][b+1]=='O')
					m=m++;
				if(tissue[a-1][b+1]=='X')
					n=n++;
				if(tissue[a+1][b]=='X'||tissue[a+1][b]=='O')
					m=m++;
				if(tissue[a+1][b]=='X')
					n=n++;
				if(tissue[a+1][b+1]=='X'||tissue[a+1][b+1]=='O')
					m=m++;
				if(tissue[a+1][b+1]=='X')
					n=n++;
				if (m==0)
					satis='N';
				else
					if (n/m>=t)
						satis='Y';
					else
						satis='N';
				if (tissue[a][b]=='X')
					tissue[a][b]='x';
				//All internal cells of grid surrounded by 8 cells.
				if ((a>0) && (b>0) && (a<row-1)&&(b<col-1))//Top row
					if(tissue[a+1][b]=='X'||tissue[a+1][b]=='O')
						m=m++;
				if(tissue[a+1][b]=='X')
					n=n++;
				if(tissue[a][b+1]=='X'||tissue[a][b+1]=='O')
					m=m++;
				if(tissue[a][b+1]=='X')
					n=n++;
				if(tissue[a+1][b+1]=='X'||tissue[a+1][b+1]=='O')
					m=m++;
				if(tissue[a+1][b+1]=='X')
					n=n++;
				if(tissue[a-1][b]=='X'||tissue[a-1][b]=='O')
					m=m++;
				if(tissue[a-1][b]=='X')
					n=n++;
				if(tissue[a-1][b+1]=='X'||tissue[a-1][b+1]=='O')
					m=m++;
				if(tissue[a-1][b+1]=='X')
					n=n++;
				if(tissue[a][b-1]=='X'||tissue[a][b-1]=='O')
					m=m++;
				if(tissue[a][b-1]=='X')
					n=n++;
				if(tissue[a-1][b-1]=='X'||tissue[a-1][b-1]=='O')
					m=m++;
				if(tissue[a-1][b-1]=='X')
					n=n++;
				if(tissue[a+1][b-1]=='X'||tissue[a+1][b-1]=='O')
					m=m++;
				if(tissue[a+1][b-1]=='X')
					n=n++;
				if (m==0)
					satis='N';
				else
					if (n/m>=t)
						satis='Y';
					else
						satis='N';
				if (tissue[a][b]=='X')
					tissue[a][b]='x';
				//Deal with O type cell, similar to X type.
				if (cellType == 'O') //Deal with O type cell here
					// 4 Corners on Grid
					if ((a==0) && (b==0)) //Top Left Corner
						if(tissue[a+1][b]=='X'||tissue[a+1][b]=='O')
							m=m++;
				if(tissue[a+1][b]=='O')
					n=n++;
				if(tissue[a][b+1]=='X'||tissue[a][b+1]=='O')
					m=m++;
				if(tissue[a][b+1]=='O')
					n=n++;
				if(tissue[a+1][b+1]=='X'||tissue[a+1][b+1]=='O')
					m=m++;
				if(tissue[a+1][b+1]=='O')
					n=n++;
				if (m==0)
					satis='N';
				else
					if (n/m>=t)
						satis='Y';
					else
						satis='N';
				if (tissue[a][b]=='O')
					tissue[a][b]='o';
				if ((a==0) && (b==col-1)) //Top Right Corner
					if(tissue[a][b-1]=='X'||tissue[a][b-1]=='O')
						m=m++;
				if(tissue[a][b-1]=='O')
					n=n++;
				if(tissue[a+1][b]=='X'||tissue[a+1][b]=='O')
					m=m++;
				if(tissue[a+1][b]=='O')
					n=n++;
				if(tissue[a+1][b-1]=='X'||tissue[a+1][b-1]=='O')
					m=m++;
				if(tissue[a+1][b-1]=='O')
					n=n++;
				if (m==0)
					satis='N';
				else
					if (n/m>=t)
						satis='Y';
					else
						satis='N';
				if (tissue[a][b]=='O')
					tissue[a][b]='o';
				if ((a==row-1) && (b==0)) //Bottom Left Corner
					if(tissue[a][b+1]=='X'||tissue[a][b+1]=='O')
						m=m++;
				if(tissue[a][b+1]=='O')
					n=n++;
				if(tissue[a-1][b]=='X'||tissue[a-1][b]=='O')
					m=m++;
				if(tissue[a-1][b]=='O')
					n=n++;
				if(tissue[a-1][b+1]=='X'||tissue[a-1][b+1]=='O')
					m=m++;
				if(tissue[a-1][b+1]=='O')
					n=n++;
				if (m==0)
					satis='N';
				else
					if (n/m>=t)
						satis='Y';
					else
						satis='N';
				if (tissue[a][b]=='O')
					tissue[a][b]='o';
				if ((a==row-1) && (b==col-1)) //Bottom Right Corner
					if(tissue[a][b-1]=='X'||tissue[a][b-1]=='O')
						m=m++;
				if(tissue[a][b-1]=='O')
					n=n++;
				if(tissue[a-1][b]=='X'||tissue[a-1][b]=='O')
					m=m++;
				if(tissue[a-1][b]=='O')
					n=n++;
				if(tissue[a-1][b-1]=='X'||tissue[a-1][b-1]=='O')
					m=m++;
				if(tissue[a-1][b-1]=='O')
					n=n++;
				if (m==0)
					satis='N';
				else
					if (n/m>=t)
						satis='Y';
					else
						satis='N';
				if (tissue[a][b]=='O')
					tissue[a][b]='o';
				//	4 Sides of the grid disregarding corners.
				if ((a==0) && ((b>0) ||(b<col-1))) //Top row
					if(tissue[a+1][b]=='X'||tissue[a+1][b]=='O')
						m=m++;
				if(tissue[a+1][b]=='O')
					n=n++;
				if(tissue[a][b-1]=='X'||tissue[a][b-1]=='O')
					m=m++;
				if(tissue[a][b-1]=='O')
					n=n++;
				if(tissue[a+1][b+1]=='X'||tissue[a+1][b+1]=='O')
					m=m++;
				if(tissue[a+1][b+1]=='O')
					n=n++;
				if(tissue[a+1][b]=='X'||tissue[a+1][b]=='O')
					m=m++;
				if(tissue[a+1][b]=='O')
					n=n++;
				if(tissue[a][b+1]=='X'||tissue[a][b+1]=='O')
					m=m++;
				if(tissue[a][b+1]=='O')
					n=n++;
				if (m==0)
					satis='N';
				else
					if (n/m>=t)
						satis='Y';
					else
						satis='N';
				if (tissue[a][b]=='O')
					tissue[a][b]='o';
				if ((a>0)||(a<row-1)&&(b==col-1)) //Right column
					if(tissue[a][b-1]=='X'||tissue[a][b-1]=='O')
						m=m++;
				if(tissue[a][b-1]=='O')
					n=n++;
				if(tissue[a+1][b]=='X'||tissue[a+1][b]=='O')
					m=m++;
				if(tissue[a+1][b]=='O')
					n=n++;
				if(tissue[a+1][b-1]=='X'||tissue[a+1][b-1]=='O')
					m=m++;
				if(tissue[a+1][b-1]=='O')
					n=n++;
				if(tissue[a][b-1]=='X'||tissue[a][b-1]=='O')
					m=m++;
				if(tissue[a][b-1]=='O')
					n=n++;
				if(tissue[a-1][b]=='X'||tissue[a-1][b]=='O')
					m=m++;
				if(tissue[a-1][b]=='O')
					n=n++;
				if (m==0)
					satis='N';
				else
					if (n/m>=t)
						satis='Y';
					else
						satis='N';
				if (tissue[a][b]=='O')
					tissue[a][b]='o';
				if ((a==row-1) && ((b>0)||(b<col-1))) //Bottom Row
					if(tissue[a][b+1]=='X'||tissue[a][b+1]=='O')
						m=m++;
				if(tissue[a][b+1]=='O')
					n=n++;
				if(tissue[a-1][b]=='X'||tissue[a-1][b]=='O')
					m=m++;
				if(tissue[a-1][b]=='O')
					n=n++;
				if(tissue[a-1][b+1]=='X'||tissue[a-1][b+1]=='O')
					m=m++;
				if(tissue[a-1][b+1]=='O')
					n=n++;
				if(tissue[a][b-1]=='X'||tissue[a][b-1]=='O')
					m=m++;
				if(tissue[a][b-1]=='O')
					n=n++;
				if(tissue[a-1][b-1]=='X'||tissue[a-1][b-1]=='O')
					m=m++;
				if(tissue[a-1][b-1]=='O')
					n=n++;
				if (m==0)
					satis='N';
				else
					if (n/m>=t)
						satis='Y';
					else
						satis='N';
				if (tissue[a][b]=='O')
					tissue[a][b]='o';
				if ((b==0)||(a<row-1) && (a>0)) //Left column
					if(tissue[a][b+1]=='X'||tissue[a][b+1]=='O')
						m=m++;
				if(tissue[a][b+1]=='O')
					n=n++;
				if(tissue[a-1][b]=='X'||tissue[a-1][b]=='O')
					m=m++;
				if(tissue[a-1][b]=='O')
					n=n++;
				if(tissue[a-1][b+1]=='X'||tissue[a-1][b+1]=='O')
					m=m++;
				if(tissue[a-1][b+1]=='O')
					n=n++;
				if(tissue[a+1][b]=='X'||tissue[a+1][b]=='O')
					m=m++;
				if(tissue[a+1][b]=='O')
					n=n++;
				if(tissue[a+1][b+1]=='X'||tissue[a+1][b+1]=='O')
					m=m++;
				if(tissue[a+1][b+1]=='O')
					n=n++;
				if (m==0)
					satis='N';
				else
					if (n/m>=t)
						satis='Y';
					else
						satis='N';
				if (tissue[a][b]=='O')
					tissue[a][b]='o';
				//All internal cells of grid surrounded by 8 cells.
				if ((a>0) && (b>0) && (a<row-1)&&(b<col-1))//Top row
					if(tissue[a+1][b]=='X'||tissue[a+1][b]=='O')
						m=m++;
				if(tissue[a+1][b]=='O')
					n=n++;
				if(tissue[a][b+1]=='X'||tissue[a][b+1]=='O')
					m=m++;
				if(tissue[a][b+1]=='O')
					n=n++;
				if(tissue[a+1][b+1]=='X'||tissue[a+1][b+1]=='O')
					m=m++;
				if(tissue[a+1][b+1]=='O')
					n=n++;
				if(tissue[a-1][b]=='X'||tissue[a-1][b]=='O')
					m=m++;
				if(tissue[a-1][b]=='O')
					n=n++;
				if(tissue[a-1][b+1]=='X'||tissue[a-1][b+1]=='O')
					m=m++;
				if(tissue[a-1][b+1]=='O')
					n=n++;
				if(tissue[a][b-1]=='X'||tissue[a][b-1]=='O')
					m=m++;
				if(tissue[a][b-1]=='O')
					n=n++;
				if(tissue[a-1][b-1]=='X'||tissue[a-1][b-1]=='O')
					m=m++;
				if(tissue[a-1][b-1]=='O')
					n=n++;
				if(tissue[a+1][b-1]=='X'||tissue[a+1][b-1]=='O')
					m=m++;
				if(tissue[a+1][b-1]=='O')
					n=n++;
				if (m==0)
					satis='N';
				else
					if (n/m>=t)
						satis='Y';
					else
						satis='N';
				if (tissue[a][b]=='O')
					tissue[a][b]='o';
				if (satis == 'N')
					satisfied = false;
				else
					satisfied = true;
			}
		}
		return satisfied;
	}
	/**
	 * Given a tissue sample, determines if all agents are satisfied.
	 * Note: Blank cells are always satisfied (as there is no agent)
	 *
	 * @param tissue a 2-D character array that has been initialized
	 * @return boolean indicating whether entire board has been satisfied (all agents)
	 **/
	public static boolean boardSatisfied(char[][] tissue, int threshold){
		int row = 10;
		int col = 10;
		for (int a = 0; a<row;a++){
			for (int b = 0;b<col;b++){
				if ((tissue[a][b] == 'x') || (tissue[a][b] == 'o'))
					return false;
			}
		}
		return false ;
	}
	/**
	 * Given a tissue sample, move all unsatisfied agents to a vacant cell
	 *
	 * @param tissue a 2-D character array that has been initialized
	 * @param threshold the percentage of like agents that must surround the agent to be satisfied
	 *
	 **/
	public static void moveAllUnsatisfied(char[][] tissue, int threshold){
	}
}
