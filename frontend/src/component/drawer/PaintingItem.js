import PaintingHeart from "../like/PaintingHeart";

const PaintingItem = (props) => {

    console.log(props)
    return (
        <div key={props.painting.paintingId}>
        <div className="my_feed_wrapper">
            <div className="items">
            <img className="items_img" src={props.painting.paintingUrl}/>
            </div>
            <div className="items_desc">
            <span className="genre">{props.painting.genre}</span>
            <span className="items_title">{props.painting.name}</span>
        <br/>
            <span>{props.painting.description}</span>
            </div>
        </div>
        </div>
    );
}

export default PaintingItem;