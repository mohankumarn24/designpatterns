package net.projectsync.designpatterns.a.creational;

/**
 * Creates families of related objects without specifying concrete classes
 *  - 'BeanFactory', 'ApplicationContext' instantiate families of beans
 *  - Provides multiple related beans without exposing concrete implementations
 */

interface ShapeAF {
	void draw();
}

class SquareAF implements ShapeAF {
	@Override
	public void draw() {
		System.out.println("Inside Square::draw() method");
	}
}

class RectangleAF implements ShapeAF {
	@Override
	public void draw() {
		System.out.println("Inside Rectangle::draw() method");
	}
}

class RoundedSquareAF implements ShapeAF {
	@Override
	public void draw() {
		System.out.println("Inside RoundedSquare::draw() method");
	}
}

class RoundedRectangleAF implements ShapeAF {
	@Override
	public void draw() {
		System.out.println("Inside RoundedRectangle::draw() method");
	}
}

// abstract factory
abstract class AbstractFactoryAF {
	abstract ShapeAF getShape(String shapeType);
}

// shape factory
class ShapeFactoryAF extends AbstractFactoryAF {
	@Override
	public ShapeAF getShape(String shapeType) {
		if (shapeType.equalsIgnoreCase("RECTANGLE")) {
			return new RectangleAF();
		} else if (shapeType.equalsIgnoreCase("SQUARE")) {
			return new SquareAF();
		}
		return null;
	}
}

// rounded shape factory
class RoundedShapeFactoryAF extends AbstractFactoryAF {
	@Override
	public ShapeAF getShape(String shapeType) {
		if (shapeType.equalsIgnoreCase("RECTANGLE")) {
			return new RoundedRectangleAF();
		} else if (shapeType.equalsIgnoreCase("SQUARE")) {
			return new RoundedSquareAF();
		}
		return null;
	}
}

// factory producer
class FactoryProducerAF {
	public static AbstractFactoryAF getFactory(boolean rounded) {
		if (rounded) {
			return new RoundedShapeFactoryAF();
		} else {
			return new ShapeFactoryAF();
		}
	}
}

// demo
public class B_AbstractFactory {
	public static void main(String[] args) {
		// get shape factory
		AbstractFactoryAF shapeFactoryAF = FactoryProducerAF.getFactory(false);
		// get an object of Shape Rectangle
		ShapeAF shape1 = shapeFactoryAF.getShape("RECTANGLE");
		// call draw method of Shape Rectangle
		shape1.draw();
		// get an object of Shape Square
		ShapeAF shape2 = shapeFactoryAF.getShape("SQUARE");
		// call draw method of Shape Square
		shape2.draw();

		// get rounded shape factory
		AbstractFactoryAF roundedShapeFactoryAF = FactoryProducerAF.getFactory(true);
		// get an object of Shape Rectangle
		ShapeAF shapeRounded1 = roundedShapeFactoryAF.getShape("RECTANGLE");
		// call draw method of Shape Rectangle
		shapeRounded1.draw();
		// get an object of Shape Square
		ShapeAF shapeRounded2 = roundedShapeFactoryAF.getShape("SQUARE");
		// call draw method of Shape Square
		shapeRounded2.draw();
	}
}
