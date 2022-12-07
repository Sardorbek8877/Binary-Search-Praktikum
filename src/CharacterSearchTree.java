public class CharacterSearchTree
{

    // Vorgaben

    private HuffmanTriple content;
    private CharacterSearchTree leftChild, rightChild;

    public CharacterSearchTree() 
    {
        content = null;
        leftChild = null;
        rightChild = null;
    }

    public HuffmanTriple getContent()
    {
        if ( !isEmpty() )
        {
            return content;
        } else {
            throw new RuntimeException();
        }
    }

    public boolean isEmpty() 
    {
        return content == null;
    }

    public boolean isLeaf() 
    {
        return !isEmpty() && leftChild.isEmpty() && rightChild.isEmpty();
    }

    public void iterativeAdd( char t )
    {
        CharacterSearchTree current = this;
        while ( !current.isEmpty() && current.content.getToken() != t )
        {
            if ( current.content.getToken() > t )
            {
                current = current.leftChild;
            }
            else
            {
                current = current.rightChild;
            }
        }
        if ( current.isEmpty() ) 
        {
            current.content = new HuffmanTriple( t );
            current.leftChild = new CharacterSearchTree();
            current.rightChild = new CharacterSearchTree();
        }
        else
        {
            current.content.incrementQuantity();
        }
    }

    public void show()
    {
        if ( !isEmpty() ) 
        {
            leftChild.show();
            System.out.println( content.toString() );
            rightChild.show();
        }
    }

    //Loesungen PraktikumBlatt_5

    public CharacterSearchTree( char[] arr){
        for ( char t : arr ){
            iterativeAdd(t);
        }
    }

    public void add( char t, int q, String c ){
        if ( isEmpty() ){
            content = new HuffmanTriple( t, q );
            content.setCode(c);
            leftChild = new CharacterSearchTree();
            rightChild = new CharacterSearchTree();
        }
        else {
            if ( content.getToken() > t ){
                leftChild.add( t, q, c );
            }
            else if (content.getToken() < t ){
                rightChild.add( t, q, c);
            }
            else {
                for( int i=0; i <= q; i++ ){
                    content.incrementQuantity();
                }
                content.setCode( c );
            }
        }
    }

    public void showPreOrder(){
        if ( !isEmpty() ){
            if ( isLeaf() ){
                System.out.println("*");
            }
            System.out.println(content.toString());
            leftChild.showPreOrder();
            rightChild.showPreOrder();
        }
    }

    public int height(){
        if ( !isEmpty()){
            int leftHeight = leftChild.height();
            int rightHeight = rightChild.height();
            if ( leftHeight > rightHeight ){
                return 1 + leftHeight;
            }
            else {
                return 1 + rightHeight;
            }
        }
        else {
            return 0;
        }
    }

    public int countCharacters(){
        if ( !isEmpty() ){
            return content.getQuantity() + leftChild.countCharacters() + rightChild.countCharacters();
        }
        else {
            return 0;
        }
    }

    public int longestCode(){
        if ( !isEmpty() )
        {
            int leftLength = leftChild.longestCode();
            int rightLength = rightChild.longestCode();
            int longestChildLength = 0;
            if ( leftLength > rightLength )
            {
                longestChildLength = leftLength;
            } else {
                longestChildLength = rightLength;
            }
            if ( longestChildLength > content.getCode().length() )
            {
                return longestChildLength;
            } else {
                return content.getCode().length();
            }
        } else {
            return 0;
        }
    }

    public HuffmanTriple minimum(){
        if ( !isEmpty() ){
            CharacterSearchTree current = this;
            while ( !current.leftChild.isEmpty() ){
                current = current.leftChild;
            }
            return current.getContent();
        }
        else {
            return null;
        }
    }

    public boolean hasOnlyCompleteNode(){
        if ( isEmpty() || isLeaf() ){
            return true;
        }
        else {
            if ( !(leftChild.isEmpty() && rightChild.isEmpty()) ){
                return leftChild.hasOnlyCompleteNode() && rightChild.hasOnlyCompleteNode();
            }
            else {
                return false;
            }
        }
    }

    public boolean containsCharacter( char t ){
        if ( !isEmpty() ){
            if( content.getToken() > t ){
                return leftChild.containsCharacter( t );
            }
            else if ( content.getToken() < t ){
                return rightChild.containsCharacter( t );
            }
            else {
                return true;
            }
        }
        return false;
    }

    public boolean equalStructure( CharacterSearchTree cst ){
        if ( isEmpty() )
        {
            return cst.isEmpty();
        } else if ( cst.isEmpty() )
        {
            return false;
        } else {
            return leftChild.equalStructure( cst.leftChild ) && rightChild.equalStructure( cst.rightChild );
        }
    }

    public CharacterSearchTree rotateNodeToRight()
    {
        CharacterSearchTree newRoot = this;
        if ( !isEmpty() && !leftChild.isEmpty() )
        {
            newRoot = leftChild;
            leftChild = newRoot.rightChild;
            newRoot.rightChild = this;
        }
        return newRoot;
    }

    public boolean samePath( char t1, char t2 )
    {
        boolean foundT1 = false;
        boolean foundT2 = false;
        CharacterSearchTree current = this;                                                ;

        while ( !current.isEmpty() )
        {
            if ( ! foundT1 )
            {
                if ( current.getContent().getToken() == t1 )
                {
                    foundT1 = true;
                }
                else
                {
                    if ( current.getContent().getToken() == t2 )
                    {
                        foundT2 = true;
                    }
                    if ( current.getContent().getToken() > t1 )
                    {
                        current = current.leftChild;
                    }
                    else
                    {
                        current = current.rightChild;
                    }
                }
            }
            else
            {
                if ( foundT2 || current.getContent().getToken() == t2 )
                {
                    return true;
                }
                if ( current.getContent().getToken() > t2 )
                {
                    current = current.leftChild;
                }
                else
                {
                    current = current.rightChild;
                }
            }
        }

        return false;
    }
}











