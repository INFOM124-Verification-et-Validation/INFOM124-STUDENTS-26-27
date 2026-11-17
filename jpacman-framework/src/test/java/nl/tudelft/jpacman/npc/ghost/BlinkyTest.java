package nl.tudelft.jpacman.npc.ghost;

import com.google.common.collect.Lists;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class BlinkyTest {
    private GhostMapParser ghostMapParser;
    private PlayerFactory playerFactory;

    @BeforeEach
    public void setUp(){
        PacManSprites pacManSprites = new PacManSprites();
        GhostFactory ghostFactory = new GhostFactory(pacManSprites);
        LevelFactory levelFactory = new LevelFactory(pacManSprites,ghostFactory);
        BoardFactory boardFactory = new BoardFactory(pacManSprites);

        ghostMapParser = new GhostMapParser(levelFactory,boardFactory,ghostFactory);
        playerFactory = new PlayerFactory(pacManSprites);
    }

    /**
     * Va vers le joueur
     */
    @Test
    public void testGoNear(){
        List<String> map = Lists.newArrayList(
            "######## ",
            "#P    B# ",
            "######## ",
            "######## "
        );

        Level level = ghostMapParser.parseMap(map);
        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.EAST);
        level.registerPlayer(player);

        Blinky blinky = Navigation.findUnitInBoard(Blinky.class,level.getBoard());
        Assertions.assertNotNull(blinky);
        Optional<Direction> directionOptimal = blinky.nextAiMove();
        assertThat(directionOptimal).isEqualTo(Optional.of(Direction.WEST));
    }

    /**
     * Pas de chemin disponnible donc ne bouge pas
     */
    @Test
    public void testNoPath(){

        List<String> map = Lists.newArrayList(
            "######## ",
            "#P #  B# ",
            "######## ",
            "######## "
        );

        Level level = ghostMapParser.parseMap(map);
        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.EAST);
        level.registerPlayer(player);

        Blinky blinky = Navigation.findUnitInBoard(Blinky.class,level.getBoard());
        Assertions.assertNotNull(blinky);
        assertThat(blinky.nextAiMove()).isEqualTo(Optional.empty());

    }

    /**
     * Pas de joueur donc pas de déplacement
     */
    @Test
    public void testNoPlayer(){

        List<String> map = Lists.newArrayList(
            "######## ",
            "#  #  B# ",
            "######## ",
            "######## "
        );

        Level level = ghostMapParser.parseMap(map);

        Blinky blinky = Navigation.findUnitInBoard(Blinky.class,level.getBoard());
        Assertions.assertNotNull(blinky);
        assertThat(blinky.nextAiMove()).isEqualTo(Optional.empty());
    }

}
