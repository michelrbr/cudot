package br.com.mxel.cuedot;

import br.com.mxel.cuedot.movies.MoviesView;

public class MainClass {

    private static ConsoleComponent _consoleComponent;

    public static void main(String[] args) {

        _consoleComponent = DaggerConsoleComponent.builder().build();
        MoviesView movies = new MoviesView();
        while (!movies.getIsRunning()) {
            movies.promptUser();
        }
    }

    public static ConsoleComponent getConsoleComponent() {

        return _consoleComponent;
    }
}
