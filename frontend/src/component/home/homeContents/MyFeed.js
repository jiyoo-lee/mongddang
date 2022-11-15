import { useEffect, useState } from "react";
import GetAxios from "../../../utils/axios/GetAxios";
import FeedList from "./FeedList";

const MyFeed = () => {

    const [paintings, setPaintings] = useState([]); 

    useEffect(()=> {
        GetAxios('/painting/following', {params:{}},(res)=>{setPaintings(res.data)})
        },[])

    return (
        <div className="my_feed_wrapper">
            <FeedList paintings={paintings}/>
        </div>
    );
}

export default MyFeed;