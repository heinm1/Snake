package model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SnakeTest {

    private Board board = new Board(100, 100);

    @Test
    public void moveRight() {
        // given
        Snake s = new Snake(board, new Point(5,5));

        // when
        s.setHorizontalVelocity(1);
        s.setVerticalVelocity(0);
        s.update();

        // then
        Assert.assertEquals(6, s.getHead().getXCord());
        Assert.assertEquals(5, s.getHead().getYCord());
    }

    @Test
    public void moveRightWrapBoard() {
        // given
        Snake s = new Snake(board, new Point(9,5));

        // when
        s.setHorizontalVelocity(1);
        s.setVerticalVelocity(0);
        s.update();

        // then
        Assert.assertEquals(0, s.getHead().getXCord());
        Assert.assertEquals(5, s.getHead().getYCord());
    }

    @Test
    public void moveLeft() {
        // given
        Snake s = new Snake(board, new Point(5,5));

        // when
        s.setHorizontalVelocity(-1);
        s.setVerticalVelocity(0);
        s.update();

        // then
        Assert.assertEquals(4, s.getHead().getXCord());
        Assert.assertEquals(5, s.getHead().getYCord());
    }

    @Test
    public void moveLeftWrapBoard() {
        // given
        Snake s = new Snake(board, new Point(0,5));

        // when
        s.setHorizontalVelocity(-1);
        s.setVerticalVelocity(0);
        s.update();

        // then
        Assert.assertEquals(9, s.getHead().getXCord());
        Assert.assertEquals(5, s.getHead().getYCord());
    }

    @Test
    public void moveUp() {
        // given
        Snake s = new Snake(board, new Point(5,5));

        // when
        s.setHorizontalVelocity(0);
        s.setVerticalVelocity(-1);
        s.update();

        // then
        Assert.assertEquals(5, s.getHead().getXCord());
        Assert.assertEquals(4, s.getHead().getYCord());
    }

    @Test
    public void moveUpWrapBoard() {
        // given
        Snake s = new Snake(board, new Point(5,0));

        // when
        s.setHorizontalVelocity(0);
        s.setVerticalVelocity(-1);
        s.update();

        // then
        Assert.assertEquals(5, s.getHead().getXCord());
        Assert.assertEquals(9, s.getHead().getYCord());
    }

    @Test
    public void moveDown() {
        // given
        Snake s = new Snake(board, new Point(5,5));

        // when
        s.setHorizontalVelocity(0);
        s.setVerticalVelocity(1);
        s.update();

        // then
        Assert.assertEquals(5, s.getHead().getXCord());
        Assert.assertEquals(6, s.getHead().getYCord());
    }

    @Test
    public void moveDownWrapBoard() {
        // given
        Snake s = new Snake(board, new Point(5,9));

        // when
        s.setHorizontalVelocity(0);
        s.setVerticalVelocity(1);
        s.update();

        // then
        Assert.assertEquals(5, s.getHead().getXCord());
        Assert.assertEquals(0, s.getHead().getYCord());
    }

    @Test
    public void grow() {
        // given
        Point p = new Point(5,5);
        Snake s = new Snake(board, p);

        // when
        s.grow();

        // then
        Assert.assertEquals(2, s.getPosition().size());
        Assert.assertTrue(p.equals(s.getPosition().get(0)));
        Assert.assertTrue(p.equals(s.getPosition().get(1)));
    }

    @Test
    public void growAndUpdate() {
        // given
        Point p1 = new Point(5,5);
        Point p2 = new Point(6,5);
        Snake s = new Snake(board, p1);

        // when
        s.grow();
        s.update();

        // then
        Assert.assertEquals(2, s.getPosition().size());
        Assert.assertTrue(p1.equals(s.getPosition().get(0)));
        Assert.assertTrue(p2.equals(s.getPosition().get(1)));
    }

    @Test
    public void isSafeTrue() {
        // given
        Snake s = new Snake(board, new Point(5,5));

        // when
        s.grow();
        s.grow();
        s.update();
        s.update();

        // then
        Assert.assertTrue(s.isSafe());
    }

    @Test
    public void isSafeFalse() {
        // given
        Snake s = new Snake(board, new Point(5,5));

        // when
        s.grow();
        s.grow();
        s.update();
        s.update();
        s.setHorizontalVelocity(-1);
        s.setVerticalVelocity(0);
        s.update();

        // then
        Assert.assertFalse(s.isSafe());
    }
}