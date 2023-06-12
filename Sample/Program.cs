using Luminis.HelloWorld;

class Program
{
    static void Main(string[] args)
    {
        Console.WriteLine("Name? ");
        string? input = Console.ReadLine();
        if (string.IsNullOrEmpty(input))
            return;
        Console.WriteLine(Hello.SayHello(input));
        Console.WriteLine();
        return;
    }
}