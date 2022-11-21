import React, { useEffect, useState } from "react";
import { useInView } from "react-intersection-observer"
import GetAxios from "../../../utils/axios/GetAxios";
import FeedItem from "../FeedItem";

const MyFeed = () => { 
    const [ref, inView] = useInView();
    const [paintings, setPaintings] = useState([]);
    const [loading, setLoading] = useState(false);
    const [page, setPage] = useState(1);

    useEffect(()=> {
        if (!loading) {
            setLoading(true);
            GetAxios('/painting/following', {params:{page: page, size:5}},(res)=>{
                setPaintings([...paintings, ...res.data]);
                setPage(page+1);
            });
            setLoading(false);
        }
        },[inView])

    return (
        <div className="my_feed_wrapper">
            {paintings.map((painting, idx) => (
                <React.Fragment key={idx}>
                {
                    paintings.length - 1 == idx ? 
                    (<div key={painting.paintingId} ref={ref}> <FeedItem painting={painting}/> </div>) : 
                    (<div key={painting.paintingId}> <FeedItem painting={painting}/> </div>)
                }
                </React.Fragment>
            ))}
        </div>
    );
}

export default MyFeed;