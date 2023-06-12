using Microsoft.VisualStudio.TestTools.UnitTesting;
using Luminis.HelloWorld;

namespace HelloLibraryTest;


[TestClass]
public class UnitTest1
{
    [TestMethod]
    public void TestSayHelloToHarry()
    {
        Assert.AreEqual("Hello Harry!", Hello.SayHello("Harry"));
    }
}