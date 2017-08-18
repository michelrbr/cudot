package br.com.mxel.cuedot;

import br.com.mxel.cuedot.movies.MoviesView;

public class MainClass {

    public static void main(String[] args) {

        ConsoleComponent consoleComponent = DaggerConsoleComponent.builder().build();
        MoviesView movies = new MoviesView(consoleComponent);
        while (!movies.getIsRunning()) {
            movies.promptUser();
        }
    }
}
