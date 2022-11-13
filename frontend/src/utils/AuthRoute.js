import { Routes, Route } from "react-router-dom";
import Feed from '../component/home/homeContents/Feed';
import Login from '../component/member/Login';

const AuthRoute = ({needAuth, path, element}) => {
    const isToken = sessionStorage.getItem("token");
    
    return <Routes> 
        needAuth
        ? ({isToken} ? <Route path={path} element={element}/> : <Route path='/login' element={<Login/>}/>)
        : ({isToken} ? <Route path='/home/feed' element={<Feed/>}/> : <Route path={path} element={element}/>)
         </Routes>;
}

export default AuthRoute;