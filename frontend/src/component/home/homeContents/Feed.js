import MyButton from "../../button/MyButton";
import Profile from "./Profile";
import Friends from "./Friends";
import { useNavigate } from "react-router-dom";


const Feed = () =>{

    const navigate = useNavigate();

    return (
        <div className="all_wrapper">
        <div className="main_wrapper">
            <div className="menu_wrapper">
                <MyButton text={'피드'} type={'nothing'} onClick={()=>{navigate('/home/feed')}}/>
                <MyButton text={'베스트'}/>
                <MyButton text={'지금 그림'}/>
                <MyButton text={'업로드'}/>
            </div>
            <div className="home_wrapper">
            </div>
        </div>
        <div className="sub_wrapper">
            <Profile/>
            <Friends/>
        </div>
        </div>
    );
}

export default Feed;