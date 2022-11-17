import FeedItem from "../FeedItem";

const FeedList = (props) =>{
    console.log(props);
    return (
        <div>
       {props.paintings.map(painting => (
            <FeedItem key={painting.paintingId} painting={painting}/>
       ))}
    </div>
    );
}

export default FeedList;