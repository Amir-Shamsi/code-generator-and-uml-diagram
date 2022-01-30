# Code Generator And UML Diagram


[![Java version](https://img.shields.io/badge/java-%5E%208-purple?style=flat-square)](https://www.python.org/)
[![BSD Licence](https://img.shields.io/badge/licence-MIT-geen?style=flat-square)](LICENSE)
[![Follow me](https://img.shields.io/github/followers/amir-shamsi?label=follow%20me&style=flat-square)](https://github.com/amir-shamsi)
<a href="https://github.com/Amir-Shamsi/code-generator-and-uml-diagram" title="Repo Size">
<img src="https://img.shields.io/github/repo-size/Amir-Shamsi/code-generator-and-uml-diagram?label=Repo%20Size&logo=Github&style=flat-square" alt="Project Initiator Repo Size"/>
</a>

<p align="left">
  <a href="https://openjfx.io/">
    <img src="src/main/resources/icons/JavaFX_logo.png" width="90"/>
  </a>
  <a href="http://www.jfoenix.com/">
    <img src="src/main/resources/icons/logo-JFX.png" width="131"/>
  </a>
  <a href="https://www.java.com/">
    <img src="src/main/resources/icons/java-card.png" width="76.5"/>
  </a>
</p>


This application allows drawing of UML diagrams in graphic user interface and generating the code in Java or Cpp. It supports:
   - Classes
   - Methods
   - Arguments

### Draw UML Graph
It's easy to draw a uml graph based on what you need only by dragging `class` or `method` or `Arg` into the board. 
<p align="center">
  <img src="uml-sc.png"/>
</p>

### Code Generating

Here is the output code of the example graph in Java
```java
abstract class FooParent
{
    abstract void printFoo();
}

class Earth
{
    public int water;
}

class Mars
{
    public float co2;
}

class Jupiter
{
    public String water;
}

class Robot
{
    void walk(int a, int b)
    {
    }
}

public class Foo extends FooParent
{
    public int water;
    public static float calculateFoo(int fooNum)
    {
    }
    public void printFoo()
    {
    }
}

class flower extends Earth
{
    public String color;
}

class Tree extends Earth
{
}


```

### Installation
1. Clone the repo
  ```sh
  git clone https://github.com/Amir-Shamsi/code-generator-and-uml-diagram.git
  ```
2. Install the dependencies from `pom.xml`
3. Enjoy!
