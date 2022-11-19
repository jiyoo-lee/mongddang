import PaintingItem from "./PaintingItem";

const DropsPaintings = (props) =>{

    return (
        <div>
            {props.paintings.map(painting => (
                <PaintingItem key={painting.paintingId} painting={painting}/>
            ))}
        </div>
    );
}

export default DropsPaintings;