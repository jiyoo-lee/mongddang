
const PaintingItem = (props) => {
    return (
        <div className="my_feed_wrapper">
            <div className="items">
            <img className="items_img" src={props.painting.url}/>
            </div>
            <div className="items_desc">
            <span>{props.painting.genre}</span>
            <span className="items_title">{props.painting.name}</span>
        <br/>
            <span>{props.painting.description}</span>
            </div>
        </div>
    );
}

export default PaintingItem;