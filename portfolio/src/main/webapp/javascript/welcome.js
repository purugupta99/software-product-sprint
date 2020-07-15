var text = ["HELLO", "NAMASTE", "HOLA", "BONJOUR", "GUTEN", "CIAO","OHAYO"];
var welcome = 0;

setInterval( change , 2000 );
function change()
{
        
        if (welcome===0)
        {
                document.getElementById("intro4").style.marginLeft="37%";
        }
        if (welcome===1)
        {
                document.getElementById("intro4").style.marginLeft="30%";
        }
        if (welcome===2)
        {
                document.getElementById("intro4").style.marginLeft="39%";
        }
        if (welcome===3)
        {
                document.getElementById("intro4").style.marginLeft="31%";
        }
        if (welcome===4)
        {
                document.getElementById("intro4").style.marginLeft="35%";
        }
        if (welcome===5)
        {
                document.getElementById("intro4").style.marginLeft="40%";
        }
        if (welcome===6)
        {
                document.getElementById("intro4").style.marginLeft="36%";
        }
        if (welcome===7)
        {
                welcome=0;
        }
        document.getElementById("intro4").innerHTML = text[welcome]+"!";
        welcome++;
}
