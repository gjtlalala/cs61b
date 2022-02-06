package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    //private int s;
    //private int t;
    //private boolean targetFound = false;
    private boolean iscycle = false;
    private Maze maze;
    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        //s = maze.xyTo1D(0, 0);
        //t = maze.xyTo1D(maze.N(), maze.N());
        //distTo[s] = 0;
        //edgeTo[s] = s;
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        dfs(0, 0);
    }
    private void dfs(int v, int f) {
        marked[v] = true;
        announce();

        /*if (v == t) {
            targetFound = true;
        }*/

        if (iscycle) {
            return;
        }

        for (int w : maze.adj(v)) {
            if((marked[w]) && w !=f ){
                iscycle = true;
                edgeTo[w] = v;
                announce();
                return;
            }
            else {
                //edgeTo[w] = v;
                announce();
                //distTo[w] = distTo[v] + 1;
                dfs(w, v);
                if (iscycle) {
                    return;
                }
            }

        }
    }
    // Helper methods go here
}

